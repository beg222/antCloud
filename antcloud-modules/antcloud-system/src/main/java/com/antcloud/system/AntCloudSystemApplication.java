package com.antcloud.system;

import com.antcloud.base.security.annotation.EnableCustomConfig;
import com.antcloud.base.security.annotation.EnableAntCloudFeignClients;
import com.antcloud.base.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author beg
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