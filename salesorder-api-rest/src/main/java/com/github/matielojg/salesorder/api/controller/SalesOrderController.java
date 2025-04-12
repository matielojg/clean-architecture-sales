package com.github.matielojg.salesorder.api.controller;

import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderResponse;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.usecase.CreateSalesOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sales-orders")
public class SalesOrderController {

    private final CreateSalesOrder createSalesOrder;

    public SalesOrderController(CreateSalesOrder createSalesOrder) {
        this.createSalesOrder = createSalesOrder;
    }

    @PostMapping
    public SalesOrderResponse create(@RequestBody SalesOrderRequest request) {
        List<CreateSalesOrder.ItemInput> items = request.getItems().stream()
                .map(i -> new CreateSalesOrder.ItemInput(i.getSkuCode(), i.getQuantity()))
                .collect(Collectors.toList());

        SalesOrder order = createSalesOrder.execute(request.getResellerId(), items);

        List<SalesOrderResponse.ItemResponse> responseItems = order.getItems().stream()
                .map(i -> new SalesOrderResponse.ItemResponse(i.getSkuCode(), i.getQuantity()))
                .collect(Collectors.toList());

        return new SalesOrderResponse(order.getId(), responseItems);
    }
}
