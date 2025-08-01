package com.skydevs.tgdrive.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.skydevs.tgdrive.dto.ConfigForm;
import com.skydevs.tgdrive.exception.ConfigFileNotFoundException;
import com.skydevs.tgdrive.result.Result;
import com.skydevs.tgdrive.service.ConfigService;
import com.skydevs.tgdrive.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigService configService;
    private final TelegramBotService telegramBotService;

    /**
     * 获取配置文件信息
     * @param name 配置文件名
     * @return ConfigForm
     */
    @SaCheckLogin
    @GetMapping()
    public Result<ConfigForm> getConfig(@RequestParam String name) {
        ConfigForm config = configService.get(name);
        if (config == null) {
            log.error("配置获取失败，请检查文件名是否错误");
            throw new ConfigFileNotFoundException();
        }
        log.info("获取数据成功");
        return Result.success(config);
    }

    /**
     * 获取所有配置文件
     * @return 配置文件列表
     */
    @SaCheckLogin
    @GetMapping("/configs")
    public Result<List<ConfigForm>> getConfigs() {
        List<ConfigForm> configForms = configService.getForms();
        return Result.success(configForms);
    }

    /**
     * 提交配置文件
     * @param configForm 配置信息
     * @return 成功消息
     */
    @SaCheckLogin
    @PostMapping()
    public Result<String> submitConfig(@RequestBody ConfigForm configForm) {
        configService.save(configForm);
        log.info("配置保存成功");
        return Result.success("配置保存成功");
    }

    /**
     * Description:
     * 删除配置文件
     * @author SkyDev
     * @date 2025-07-30 16:46:09
     * @param name 配置文件名
     * @return 成功消息
     */
    @SaCheckLogin
    @DeleteMapping("/{name}")
    public Result<String> deleteConfig(@PathVariable("name") String name) {
        configService.delete(name);
        log.info("配置删除成功");
        return Result.success("配置删除成功");
    }

    /**
     * Description:
     * 加载配置
     * @param filename 配置文件名
     * @return 成功消息
     */
    @SaCheckLogin
    @GetMapping("/{filename}")
    public Result<String> loadConfig(@PathVariable("filename") String filename) {
        telegramBotService.initializeBot(filename);
        log.info("加载配置成功");
        return Result.success("配置加载成功");
    }
}
