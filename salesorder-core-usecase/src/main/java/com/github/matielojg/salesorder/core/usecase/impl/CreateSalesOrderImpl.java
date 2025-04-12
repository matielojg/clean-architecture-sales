package com.github.matielojg.salesorder.core.usecase.impl;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrderItem;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import com.github.matielojg.salesorder.core.gateway.DistributorGateway;
import com.github.matielojg.salesorder.core.usecase.CreateSalesOrder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateSalesOrderImpl implements CreateSalesOrder {

    private final SalesOrderRepository salesOrderRepository;
    private final DistributorGateway distributorGateway;

    public CreateSalesOrderImpl(SalesOrderRepository salesOrderRepository,
                                DistributorGateway distributorGateway) {
        this.salesOrderRepository = salesOrderRepository;
        this.distributorGateway = distributorGateway;
    }

    @Override
    public SalesOrder execute(UUID resellerId, List<ItemInput> items) {
        List<SalesOrderItem> domainItems = items.stream()
                .map(i -> new SalesOrderItem(i.skuCode(), i.quantity()))
                .collect(Collectors.toList());

        SalesOrder order = new SalesOrder(resellerId, domainItems);

        salesOrderRepository.save(order);

        distributorGateway.send(order);

        return order;
    }
}
