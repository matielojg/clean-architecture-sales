package com.github.matielojg.salesorder.adapter.repository.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sales_orders")
public class SalesOrderEntity {

    @Id
    private UUID id;

    private UUID resellerId;

    @Enumerated(EnumType.STRING)
    private OrderStatusEntity status;

    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesOrderItemEntity> items;

    // Getters, setters, constructors

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getResellerId() {
        return resellerId;
    }

    public void setResellerId(UUID resellerId) {
        this.resellerId = resellerId;
    }

    public OrderStatusEntity getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEntity status) {
        this.status = status;
    }

    public List<SalesOrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<SalesOrderItemEntity> items) {
        this.items = items;
    }
}
