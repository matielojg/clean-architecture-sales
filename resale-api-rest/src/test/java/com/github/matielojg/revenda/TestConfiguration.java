package com.github.matielojg.revenda;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication(
    scanBasePackages = "com.github.matielojg.revenda",
    exclude = {
        org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration.class
    }
)
public class TestConfiguration {
    
    @Bean
    @Primary
    public MeterRegistry meterRegistry() {
        return new SimpleMeterRegistry();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(TestConfiguration.class, args);
    }
}