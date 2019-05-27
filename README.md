### hikvision-security-platform

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.mengwang0211/hikvision-security-platform/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.mengwang0211/hikvision-security-platform/)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

#### 海康威视综合安防平台 JAVA SDK封装 - SpringBoot

##### 完成功能

    1. 视频对接

#### 依赖

    lombok

#### 用法

##### pom.xml引入[已上传到中央仓库]

    <dependency>
        <groupId>com.github.mengwang0211</groupId>
        <artifactId>hikvision-security-platform</artifactId>
        <version>1.0.0</version>
    </dependency>    
    
    
##### application.yml
    hikvision:
        cameras:
            host: xxxxxxxx
            appKey: xxxxxx
            appSecret: xxxxx
                
                
###### 发送消息

        @Autowired
        CamerasHandler camerasHandler;
        
     
                

        