package com.github.matielojg.salesorder.adapter.repository.mapper;

import com.github.matielojg.salesorder.adapter.repository.entity.OrderStatusEntity;
import com.github.matielojg.salesorder.adapter.repository.entity.SalesOrderEntity;
import com.github.matielojg.salesorder.adapter.repository.entity.SalesOrderItemEntity;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrderItem;
import com.github.matielojg.salesorder.core.domain.vo.SalesOrderStatus;

import java.util.List;

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
            itemEntity.setSkuCode(item.skuCode());
            itemEntity.setQuantity(item.quantity());
            itemEntity.setSalesOrder(entity);
            return itemEntity;
        }).toList();

        entity.setItems(items);
        return entity;
    }

    public static SalesOrder toDomain(SalesOrderEntity entity) {
        List<SalesOrderItem> items = entity.getItems().stream().map(item -> new SalesOrderItem(item.getSkuCode(), item.getQuantity())).toList();

        return new SalesOrder(entity.getId(), entity.getResellerId(), items, SalesOrderStatus.valueOf(entity.getStatus().name()));
    }
}
