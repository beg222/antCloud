package com.laigeoffer.antcloud.gen;

import com.laigeoffer.antcloud.base.security.annotation.EnableCustomConfig;
import com.laigeoffer.antcloud.base.security.annotation.EnableAntCloudFeignClients;
import com.laigeoffer.antcloud.base.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author canghe
 * @description 代码生成
 * @create 2024-04-25-14:18
 */
@EnableCustomConfig
@EnableAntCloudFeignClients
@EnableCustomSwagger2
@SpringBootApplication
public class AntCloudGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(AntCloudGenApplication.class, args);
    }
}