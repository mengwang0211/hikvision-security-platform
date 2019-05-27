package io.github.wangmeng.isc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "hikvision.cameras")
public class CamerasProperties {

    /**
    *  主机地址
    */
    private String host;


    /**
    *  appKey
    */
    private String appKey;

    /**
    *  appSecret
    */
    private String appSecret;
}
