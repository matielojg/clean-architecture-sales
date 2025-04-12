package com.github.matielojg.salesorder.core.domain.entity;

import java.util.Objects;

public class SalesOrderItem {

    private final String skuCode;
    private final int quantity;

    public SalesOrderItem(String skuCode, int quantity) {
        if (skuCode == null || skuCode.isBlank()) {
            throw new IllegalArgumentException("SKU code must not be empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        this.skuCode = skuCode;
        this.quantity = quantity;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesOrderItem)) return false;
        SalesOrderItem that = (SalesOrderItem) o;
        return skuCode.equals(that.skuCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuCode);
    }
}
