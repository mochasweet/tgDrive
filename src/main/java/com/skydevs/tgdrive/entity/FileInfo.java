package com.skydevs.tgdrive.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfo {
    private String fileName;         // 文件名
    private String downloadUrl;      // 文件下载URL
    private String size;
    private Long fullSize;
    private String fileId;

    // 用于存储上传时间的 UNIX 时间戳
    private Long uploadTime;

}

