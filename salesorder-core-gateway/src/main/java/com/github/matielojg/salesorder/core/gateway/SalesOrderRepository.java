package com.github.matielojg.salesorder.core.gateway;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;

public interface SalesOrderRepository {
    void save(SalesOrder order);
}
