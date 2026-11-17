package com.antcloud.workflow;

import com.antcloud.base.security.annotation.EnableCustomConfig;
import com.antcloud.base.security.annotation.EnableDistributedLock;
import com.antcloud.base.security.annotation.EnableAntCloudFeignClients;
import com.antcloud.base.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author beg
 * @description 工作流服务
 * @create 2024-04-25-17:45
 */
@EnableCustomConfig
@EnableAntCloudFeignClients
@EnableCustomSwagger2
@EnableDistributedLock // 启用Redisson分布式锁
@SpringBootApplication
public class AntCloudWorkflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(AntCloudWorkflowApplication.class, args);
    }
}