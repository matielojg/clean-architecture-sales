package com.github.matielojg.salesorder.core.usecase.impl;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.vo.SalesOrderStatus;
import com.github.matielojg.salesorder.core.gateway.DistributorGateway;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import com.github.matielojg.salesorder.core.usecase.ReprocessFailedSalesOrders;

import java.util.List;
import java.util.Objects;

public class ReprocessFailedSalesOrdersImpl implements ReprocessFailedSalesOrders {

    private final SalesOrderRepository repository;
    private final DistributorGateway distributor;

    public ReprocessFailedSalesOrdersImpl(SalesOrderRepository repository, DistributorGateway distributor) {
        this.repository = Objects.requireNonNull(repository);
        this.distributor = Objects.requireNonNull(distributor);
    }

    @Override
    public void execute() {
        List<SalesOrder> failedOrders = repository.findByStatus(SalesOrderStatus.FAILED);

        for (SalesOrder order : failedOrders) {
            try {
                distributor.send(order);
                repository.updateStatus(order.getId(), SalesOrderStatus.SENT);
                // log de domínio real vai pra camada externa (ex: controller ou listener)
            } catch (Exception e) {
                // mesma coisa, aqui apenas tratamos a exceção
                // uma alternativa é propagar para uma camada superior ou salvar histórico, se aplicável
            }
        }
    }
}
