package com.github.matielojg.salesorder.core.usecase.impl;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrderItem;
import com.github.matielojg.salesorder.core.gateway.DistributorGateway;
import com.github.matielojg.salesorder.core.gateway.LoggerGateway;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import com.github.matielojg.salesorder.core.usecase.CreateSalesOrder;

import java.util.List;
import java.util.UUID;

public class CreateSalesOrderImpl implements CreateSalesOrder {

    private final SalesOrderRepository repository;
    private final DistributorGateway distributorGateway;
    private final LoggerGateway log;

    public CreateSalesOrderImpl(SalesOrderRepository repository,
                                DistributorGateway distributorGateway,
                                LoggerGateway log) {
        this.repository = repository;
        this.distributorGateway = distributorGateway;
        this.log = log;
    }

    @Override
    public SalesOrder execute(UUID resellerId, List<ItemInput> items) {

        if (resellerId == null || items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Reseller ID e itens são obrigatórios");
        }

        SalesOrder order = new SalesOrder(
                resellerId,
                items.stream()
                        .map(i -> new SalesOrderItem(i.skuCode(), i.quantity()))
                        .toList()
        );

        log.debug("✅ SalesOrder criado: {}", order);

        distributorGateway.send(order);
        log.debug("📨 Pedido enviado ao distribuidor");

        repository.save(order);
        log.debug("💾 Pedido salvo com ID: {}", order.getId());

        return order;
    }
}
