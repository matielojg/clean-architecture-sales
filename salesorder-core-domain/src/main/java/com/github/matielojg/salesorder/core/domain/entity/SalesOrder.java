package com.github.matielojg.salesorder.core.domain.entity;

import com.github.matielojg.salesorder.core.domain.exception.InvalidSalesOrderException;
import com.github.matielojg.salesorder.core.domain.vo.SalesOrderStatus;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SalesOrder {

    private final UUID id;
    private final UUID resellerId;
    private final List<SalesOrderItem> items;
    private SalesOrderStatus status;

    public SalesOrder(UUID id, UUID resellerId, List<SalesOrderItem> items, SalesOrderStatus status) {
        this.id = id;
        this.resellerId = resellerId;
        this.items = Collections.unmodifiableList(items);
        this.status = status;
    }

    public SalesOrder(UUID resellerId, List<SalesOrderItem> items) {
        if (resellerId == null) {
            throw new InvalidSalesOrderException("Reseller ID must not be null");
        }
        if (items == null || items.isEmpty()) {
            throw new InvalidSalesOrderException("Sales order must contain at least one item");
        }

        int totalQuantity = items.stream()
                .mapToInt(SalesOrderItem::quantity)
                .sum();

        if (totalQuantity < 1000) {
            throw new InvalidSalesOrderException("Sales order must contain at least 1000 units in total");
        }

        this.id = UUID.randomUUID();
        this.resellerId = resellerId;
        this.items = Collections.unmodifiableList(items);
        this.status = SalesOrderStatus.CREATED;
    }

    public UUID getId() {
        return id;
    }

    public UUID getResellerId() {
        return resellerId;
    }

    public List<SalesOrderItem> getItems() {
        return items;
    }

    public SalesOrderStatus getStatus() {
        return status;
    }

    public void markAsSent() {
        this.status = SalesOrderStatus.SENT;
    }
}
