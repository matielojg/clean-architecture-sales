package com.github.matielojg.salesorder.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.github.matielojg.salesorder.core",
        "com.github.matielojg.salesorder.adapter",
        "com.github.matielojg.salesorder.api"})
@EnableJpaRepositories(basePackages = "com.github.matielojg.salesorder.adapter.repository")
@EntityScan(basePackages = "com.github.matielojg.salesorder.adapter.repository.entity")
public class SalesOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SalesOrderApplication.class, args);
    }
}
