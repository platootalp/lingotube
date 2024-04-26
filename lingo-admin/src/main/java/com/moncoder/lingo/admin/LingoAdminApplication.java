package com.moncoder.lingo.admin;

import com.moncoder.lingo.admin.config.DefaultFeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableFeignClients(defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication(scanBasePackages = {"com.moncoder.lingo"})
public class LingoAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingoAdminApplication.class, args);
    }

}
