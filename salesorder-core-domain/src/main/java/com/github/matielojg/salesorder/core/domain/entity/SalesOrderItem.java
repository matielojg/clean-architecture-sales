package com.github.matielojg.salesorder.core.domain.entity;

import java.util.Objects;

public record SalesOrderItem(String skuCode, int quantity) {

    public SalesOrderItem {
        if (skuCode == null || skuCode.isBlank()) {
            throw new IllegalArgumentException("SKU code must not be empty");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesOrderItem that)) return false;
        return skuCode.equals(that.skuCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuCode);
    }
}
