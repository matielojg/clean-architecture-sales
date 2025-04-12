package com.github.matielojg.salesorder.core.usecase;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;

import java.util.UUID;
import java.util.List;

public interface CreateSalesOrder {
    SalesOrder execute(UUID resellerId, List<ItemInput> items);

    record ItemInput(String skuCode, int quantity) {}
}
