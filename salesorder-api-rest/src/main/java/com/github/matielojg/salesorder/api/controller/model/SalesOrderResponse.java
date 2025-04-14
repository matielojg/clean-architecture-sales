package com.github.matielojg.salesorder.api.controller.model;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

@Schema(description = "Response with created sales order data")
public record SalesOrderResponse(

        @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID orderId,

        @Schema(description = "List of items")
        List<ItemResponse> items
) {

    public static SalesOrderResponse fromDomain(SalesOrder order) {
        List<ItemResponse> itemResponses = order.getItems().stream()
                .map(i -> new ItemResponse(i.getSkuCode(), i.getQuantity()))
                .toList();

        return new SalesOrderResponse(order.getId(), itemResponses);
    }

    @Schema(description = "Item included in the sales order")
    public record ItemResponse(

            @Schema(description = "SKU code of the item", example = "SKU-123")
            String skuCode,

            @Schema(description = "Quantity of the item", example = "500")
            int quantity

    ) {
    }
}

