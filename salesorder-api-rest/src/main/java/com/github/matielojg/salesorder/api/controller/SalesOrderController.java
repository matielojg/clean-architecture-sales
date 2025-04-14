package com.github.matielojg.salesorder.api.controller;

import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderResponse;
import com.github.matielojg.salesorder.api.openapi.SalesOrderApi;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.vo.SalesOrderStatus;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import com.github.matielojg.salesorder.core.usecase.CreateSalesOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-orders")
@SuppressWarnings("unused")
public class SalesOrderController implements SalesOrderApi {

    private final CreateSalesOrder createSalesOrder;
    private final SalesOrderRepository repository;

    @SuppressWarnings("unused")
    public SalesOrderController(CreateSalesOrder createSalesOrder, SalesOrderRepository repository) {
        this.createSalesOrder = createSalesOrder;
        this.repository = repository;
    }

    @GetMapping
    public List<SalesOrderResponse> listByStatus(@RequestParam(required = false) SalesOrderStatus status) {
        List<SalesOrder> orders = (status != null) ? repository.findByStatus(status) : List.of();

        return orders.stream().map(order -> new SalesOrderResponse(
                order.getId(), order.getItems().stream()
                .map(i -> new SalesOrderResponse.ItemResponse(
                        i.skuCode(), i.quantity())).toList())).toList();
    }

    @Override
    public ResponseEntity<SalesOrderResponse> create(@RequestBody SalesOrderRequest request) {
        SalesOrder order = createSalesOrder.execute(
                request.getResellerId(),
                request.getItems().stream()
                        .map(i -> new CreateSalesOrder.ItemInput(i.getSkuCode(), i.getQuantity()))
                        .toList()
        );

        return ResponseEntity
                .status(201)
                .body(SalesOrderResponse.fromDomain(order));
    }

}
