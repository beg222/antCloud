package com.laigeoffer.antcloud.job;

import com.laigeoffer.antcloud.base.security.annotation.EnableCustomConfig;
import com.laigeoffer.antcloud.base.security.annotation.EnableAntCloudFeignClients;
import com.laigeoffer.antcloud.base.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author canghe
 * @description 定时任务
 * @create 2024-04-25-15:13
 */
@EnableCustomConfig
@EnableAntCloudFeignClients
@EnableCustomSwagger2
@SpringBootApplication
public class AntCloudJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(AntCloudJobApplication.class, args);
    }
}