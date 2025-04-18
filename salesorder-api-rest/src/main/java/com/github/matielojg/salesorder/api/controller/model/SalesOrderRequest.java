package com.github.matielojg.salesorder.api.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Schema(description = "Request to create a new sales order")
public record SalesOrderRequest(


        @Schema(description = "Reseller identifier", example = "8a05d1b9-1c68-4b93-a3a3-619202dca3f6")
        @NotNull
        UUID resellerId,

        @NotNull
        @Valid
        @Schema(description = "List of order items")
        List<ItemRequest> items
) {

    @Schema(description = "Item in a sales order")
    public record ItemRequest(

            @NotNull
            @Valid
            @Schema(example = "SKU-123")
            String skuCode,

            @NotNull
            @Valid
            @Schema(example = "1000")
            Integer quantity
    ) {
    }
}
