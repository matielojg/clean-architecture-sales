package com.github.matielojg.salesorder.api.controller.model;

import java.util.List;
import java.util.UUID;

public class SalesOrderRequest {
    private UUID resellerId;
    private List<ItemRequest> items;

    public UUID getResellerId() {
        return resellerId;
    }

    public void setResellerId(UUID resellerId) {
        this.resellerId = resellerId;
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }

    public static class ItemRequest {
        private String skuCode;
        private int quantity;

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
    }
}
