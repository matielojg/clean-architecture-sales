package com.github.matielojg.salesorder.api.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Schema(description = "Request to create a new sales order")
public class SalesOrderRequest {

    @NotNull
    @Schema(description = "Reseller identifier", example = "8a05d1b9-1c68-4b93-a3a3-619202dca3f6")
    private UUID resellerId;

    @NotNull
    @Schema(description = "List of order items")
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

    @Schema(description = "Item in a sales order")
    public static class ItemRequest {
        @Schema(example = "SKU-123")
        @NotNull
        private String skuCode;

        @Schema(example = "1000")
        @NotNull
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
