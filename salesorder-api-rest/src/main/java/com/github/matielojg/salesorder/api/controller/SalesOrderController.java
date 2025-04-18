package com.github.matielojg.salesorder.api.controller;

import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderResponse;
import com.github.matielojg.salesorder.api.openapi.SalesOrderApi;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.exception.InvalidSalesOrderException;
import com.github.matielojg.salesorder.core.domain.vo.SalesOrderStatus;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import com.github.matielojg.salesorder.core.usecase.CreateSalesOrder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
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

    @Override
    public List<SalesOrderResponse> listByStatus(@RequestParam(required = false) SalesOrderStatus status) {
        List<SalesOrder> orders = (status != null) ? repository.findByStatus(status) : List.of();

        return orders.stream().map(order -> new SalesOrderResponse(
                order.getId(), order.getItems().stream()
                .map(i -> new SalesOrderResponse.ItemResponse(
                        i.skuCode(), i.quantity())).toList())).toList();
    }

    @Override
    public ResponseEntity<SalesOrderResponse> create(@Valid @RequestBody SalesOrderRequest request) {
        if (request.items() == null) {
            throw new InvalidSalesOrderException("Items list must not be null");
        }
        SalesOrder order = createSalesOrder.execute(
                request.resellerId(),
                request.items().stream()
                        .map(i -> new CreateSalesOrder.ItemInput(i.skuCode(), i.quantity()))
                        .toList()
        );

        return ResponseEntity
                .status(201)
                .body(SalesOrderResponse.fromDomain(order));
    }
}
