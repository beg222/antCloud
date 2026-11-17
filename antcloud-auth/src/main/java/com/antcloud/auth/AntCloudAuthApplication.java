package com.antcloud.auth;

import com.antcloud.base.security.annotation.EnableAntCloudFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author beg
 * @description 认证授权中心
 * @create 2024-04-23-15:00
 */
@EnableAntCloudFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AntCloudAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AntCloudAuthApplication.class, args);
    }
}