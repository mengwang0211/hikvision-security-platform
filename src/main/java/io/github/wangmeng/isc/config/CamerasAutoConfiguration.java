package io.github.wangmeng.isc.config;

import io.github.wangmeng.isc.handler.CamerasHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CamerasProperties.class)
@ConditionalOnClass(CamerasConfiguration.class)
@ConditionalOnProperty(prefix = "hikvision.cameras", value = "enabled", matchIfMissing = true)
public class CamerasAutoConfiguration {

    @Autowired
    private CamerasProperties camerasProperties;


    @Bean
    @ConditionalOnMissingBean(CamerasConfiguration.class)
    public CamerasConfiguration camerasConfiguration() {
        CamerasConfiguration camerasConfiguration = new CamerasConfiguration();
        camerasConfiguration.setHost(camerasProperties.getHost());
        camerasConfiguration.setAppKey(camerasProperties.getAppKey());
        camerasConfiguration.setAppSecret(camerasConfiguration.getHost());
        return camerasConfiguration;
    }




    @Bean
    @ConditionalOnMissingBean(CamerasHandler.class)
    public CamerasHandler camerasHandler() {
        return new CamerasHandler();
    }


}
