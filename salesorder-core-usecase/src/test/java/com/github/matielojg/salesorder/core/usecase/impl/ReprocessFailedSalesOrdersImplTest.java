package com.github.matielojg.salesorder.core.usecase.impl;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrderItem;
import com.github.matielojg.salesorder.core.domain.vo.SalesOrderStatus;
import com.github.matielojg.salesorder.core.gateway.DistributorGateway;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

class ReprocessFailedSalesOrdersImplTest {

    private SalesOrderRepository repository;
    private DistributorGateway distributor;
    private ReprocessFailedSalesOrdersImpl usecase;

    @BeforeEach
    void setUp() {
        repository = mock(SalesOrderRepository.class);
        distributor = mock(DistributorGateway.class);
        usecase = new ReprocessFailedSalesOrdersImpl(repository, distributor);
    }

    @Test
    void shouldReprocessAllFailedOrders() {
        SalesOrder order = new SalesOrder(
                UUID.randomUUID(),
                List.of(new SalesOrderItem("SKU-1", 1000))
        );

        when(repository.findByStatus(SalesOrderStatus.FAILED))
                .thenReturn(List.of(order));

        usecase.execute();

        verify(distributor).send(order);
        verify(repository).updateStatus(order.getId(), SalesOrderStatus.SENT);
    }

    @Test
    void shouldNotUpdateStatusIfSendFails() {
        SalesOrder order = new SalesOrder(
                UUID.randomUUID(),
                List.of(new SalesOrderItem("SKU-2", 1000))
        );

        when(repository.findByStatus(SalesOrderStatus.FAILED))
                .thenReturn(List.of(order));

        doThrow(new RuntimeException("fail")).when(distributor).send(order);

        usecase.execute();

        verify(distributor).send(order);
        verify(repository, never()).updateStatus(any(), any());
    }
}
