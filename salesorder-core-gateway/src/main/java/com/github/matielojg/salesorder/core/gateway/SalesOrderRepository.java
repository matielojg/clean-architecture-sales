package com.github.matielojg.salesorder.core.gateway;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.vo.SalesOrderStatus;

import java.util.List;
import java.util.UUID;

public interface SalesOrderRepository {
    void save(SalesOrder order);

    List<SalesOrder> findByStatus(SalesOrderStatus salesOrderStatus);

    void updateStatus(UUID orderId, SalesOrderStatus status);

}
