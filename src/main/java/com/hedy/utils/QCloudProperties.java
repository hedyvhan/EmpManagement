package com.hedy.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.tengxun")
public class QCloudProperties {
    private String SecretId;
    private String SecretKey;
    private String region;
    private String url;
    private String bucketName;
}
