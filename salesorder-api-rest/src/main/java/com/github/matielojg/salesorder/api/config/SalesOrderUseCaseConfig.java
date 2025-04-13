package com.github.matielojg.salesorder.api.config;

import com.github.matielojg.salesorder.core.gateway.DistributorGateway;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import com.github.matielojg.salesorder.core.usecase.CreateSalesOrder;
import com.github.matielojg.salesorder.core.usecase.impl.CreateSalesOrderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesOrderUseCaseConfig {

    @Bean
    public CreateSalesOrder createSalesOrder(SalesOrderRepository repository,
                                             DistributorGateway distributorGateway) {
        return new CreateSalesOrderImpl(repository, distributorGateway);
    }
}
