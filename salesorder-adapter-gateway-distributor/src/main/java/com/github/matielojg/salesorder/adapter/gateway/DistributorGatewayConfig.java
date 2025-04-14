package com.github.matielojg.salesorder.adapter.gateway;

import com.github.matielojg.salesorder.core.gateway.DistributorGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DistributorGatewayConfig {

    @Bean
    public DistributorGateway distributorGateway(RestTemplate restTemplate, @Value("${distributor.url}") String url) {
        return new DistributorHttpClient(restTemplate, url);
    }
}
