package com.github.matielojg.salesorder.api.config;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrderItem;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Profile({"dev", "docker"})
public class SalesDataLoader implements CommandLineRunner {

    private final SalesOrderRepository repository;

    public SalesDataLoader(SalesOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        int qtde = 10;

        for (int i = 1; i <= qtde; i++) {
            UUID resellerId = UUID.randomUUID();

            List<SalesOrderItem> items = List.of(
                    new SalesOrderItem("SKU-" + String.format("%03d", i * 2), 500),
                    new SalesOrderItem("SKU-" + String.format("%03d", i * 2 + 1), 600)
            );

            SalesOrder order = new SalesOrder(resellerId, items);
            repository.save(order);
        }
    }
}
