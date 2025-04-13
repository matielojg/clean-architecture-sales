package com.github.matielojg.salesorder.adapter.repository.mapper;

import com.github.matielojg.salesorder.adapter.repository.entity.*;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrderItem;
import com.github.matielojg.salesorder.core.domain.vo.OrderStatus;

import java.util.List;
import java.util.stream.Collectors;

public class SalesOrderMapper {

    private SalesOrderMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static SalesOrderEntity toEntity(SalesOrder domain) {
        SalesOrderEntity entity = new SalesOrderEntity();
        entity.setId(domain.getId());
        entity.setResellerId(domain.getResellerId());
        entity.setStatus(OrderStatusEntity.valueOf(domain.getStatus().name()));

        List<SalesOrderItemEntity> items = domain.getItems().stream().map(item -> {
            SalesOrderItemEntity itemEntity = new SalesOrderItemEntity();
            itemEntity.setSkuCode(item.getSkuCode());
            itemEntity.setQuantity(item.getQuantity());
            itemEntity.setSalesOrder(entity);
            return itemEntity;
        }).collect(Collectors.toList());

        entity.setItems(items);
        return entity;
    }
}
