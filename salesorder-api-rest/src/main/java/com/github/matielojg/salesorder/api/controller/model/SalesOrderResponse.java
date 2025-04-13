package com.github.matielojg.salesorder.api.controller.model;

import java.util.List;
import java.util.UUID;

public class SalesOrderResponse {
    private final UUID orderId;
    private final List<ItemResponse> items;

    public SalesOrderResponse(UUID orderId, List<ItemResponse> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<ItemResponse> getItems() {
        return items;
    }

    public static class ItemResponse {
        private final String skuCode;
        private final int quantity;

        public ItemResponse(String skuCode, int quantity) {
            this.skuCode = skuCode;
            this.quantity = quantity;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
