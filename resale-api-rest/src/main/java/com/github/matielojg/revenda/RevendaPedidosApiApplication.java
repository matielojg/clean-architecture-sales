package com.github.matielojg.revenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = "com.github.matielojg.revenda",
    exclude = {
        org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration.class
    }
)
public class RevendaPedidosApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(RevendaPedidosApiApplication.class, args);
    }
}
