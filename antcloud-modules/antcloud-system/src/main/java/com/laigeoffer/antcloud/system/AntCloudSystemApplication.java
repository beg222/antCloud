package com.laigeoffer.antcloud.system;

import com.laigeoffer.antcloud.base.security.annotation.EnableCustomConfig;
import com.laigeoffer.antcloud.base.security.annotation.EnableAntCloudFeignClients;
import com.laigeoffer.antcloud.base.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author canghe
 * @description 系统模块
 * @create 2024-04-24-15:29
 */
@EnableCustomConfig
@EnableAntCloudFeignClients
@EnableCustomSwagger2
@SpringBootApplication
public class AntCloudSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(AntCloudSystemApplication.class, args);
    }
}