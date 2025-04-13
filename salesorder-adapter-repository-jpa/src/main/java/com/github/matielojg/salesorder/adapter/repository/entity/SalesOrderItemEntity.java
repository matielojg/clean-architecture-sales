package com.github.matielojg.salesorder.adapter.repository.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "sales_order_items")
public class SalesOrderItemEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String skuCode;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_order_id")
    private SalesOrderEntity salesOrder;

    // Getters, setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SalesOrderEntity getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrderEntity salesOrder) {
        this.salesOrder = salesOrder;
    }
}
