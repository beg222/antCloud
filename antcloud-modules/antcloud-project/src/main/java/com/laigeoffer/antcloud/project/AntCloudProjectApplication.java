package com.laigeoffer.antcloud.project;

import com.laigeoffer.antcloud.base.security.annotation.EnableCustomConfig;
import com.laigeoffer.antcloud.base.security.annotation.EnableAntCloudFeignClients;
import com.laigeoffer.antcloud.base.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author canghe
 * @description 项目管理模块
 * @create 2024-04-25-17:23
 */
@EnableCustomConfig
@EnableAntCloudFeignClients
@EnableCustomSwagger2
@SpringBootApplication
public class AntCloudProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(AntCloudProjectApplication.class, args);
    }
}