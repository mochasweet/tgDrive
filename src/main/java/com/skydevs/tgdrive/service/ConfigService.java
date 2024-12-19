package com.skydevs.tgdrive.service;

import com.skydevs.tgdrive.dto.ConfigForm;

import java.util.List;

public interface ConfigService {

    ConfigForm get(String filename);

    void save(ConfigForm configForm);

    List<ConfigForm> getForms();
}
