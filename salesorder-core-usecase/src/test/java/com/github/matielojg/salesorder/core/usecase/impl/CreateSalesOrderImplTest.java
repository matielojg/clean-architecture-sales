package com.github.matielojg.salesorder.core.usecase.impl;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.gateway.LoggerGateway;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import com.github.matielojg.salesorder.core.gateway.DistributorGateway;
import com.github.matielojg.salesorder.core.usecase.CreateSalesOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CreateSalesOrderImplTest {

    private SalesOrderRepository salesOrderRepository;
    private DistributorGateway distributorGateway;
    private CreateSalesOrder createSalesOrder;

    @BeforeEach
    void setUp() {
        salesOrderRepository = mock(SalesOrderRepository.class);
        distributorGateway = mock(DistributorGateway.class);
        LoggerGateway log = mock(LoggerGateway.class);
        createSalesOrder = new CreateSalesOrderImpl(salesOrderRepository, distributorGateway, log);
    }

    @Test
    void shouldCreateAndSendSalesOrderSuccessfully() {
        UUID resellerId = UUID.randomUUID();
        List<CreateSalesOrder.ItemInput> items = List.of(
                new CreateSalesOrder.ItemInput("SKU-123", 600),
                new CreateSalesOrder.ItemInput("SKU-456", 500)
        );

        SalesOrder order = createSalesOrder.execute(resellerId, items);

        assertNotNull(order.getId());
        assertEquals(resellerId, order.getResellerId());
        assertEquals(2, order.getItems().size());

        verify(salesOrderRepository).save(order);
        verify(distributorGateway).send(order);
    }

    @Test
    void shouldFailWhenTotalQuantityIsBelowMinimum() {
        UUID resellerId = UUID.randomUUID();
        List<CreateSalesOrder.ItemInput> items = List.of(
                new CreateSalesOrder.ItemInput("SKU-001", 300)
        );

        assertThrows(RuntimeException.class, () -> createSalesOrder.execute(resellerId, items));

        verifyNoInteractions(salesOrderRepository);
        verifyNoInteractions(distributorGateway);
    }
}
