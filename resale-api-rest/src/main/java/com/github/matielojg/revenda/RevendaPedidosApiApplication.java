package com.github.matielojg.revenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.github.matielojg.revenda")
public class RevendaPedidosApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RevendaPedidosApiApplication.class, args);
    }
}
