package com.github.matielojg.salesorder.api.controller;

import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderResponse;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.vo.SalesOrderStatus;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import com.github.matielojg.salesorder.core.usecase.CreateSalesOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-orders")
public class SalesOrderController {


    private static final Logger log = LoggerFactory.getLogger(SalesOrderController.class);
    private final CreateSalesOrder createSalesOrder;
    private final SalesOrderRepository repository;

    public SalesOrderController(CreateSalesOrder createSalesOrder, SalesOrderRepository repository) {
        this.createSalesOrder = createSalesOrder;
        this.repository = repository;
    }

    @GetMapping
    public List<SalesOrderResponse> listByStatus(@RequestParam(required = false) SalesOrderStatus status) {
        List<SalesOrder> orders = (status != null)
                ? repository.findByStatus(status)
                : List.of();

        return orders.stream()
                .map(order -> new SalesOrderResponse(
                        order.getId(),
                        order.getItems().stream()
                                .map(i -> new SalesOrderResponse.ItemResponse(i.getSkuCode(), i.getQuantity()))
                                .toList()
                ))
                .toList();
    }

    @PostMapping
    public SalesOrderResponse create(@RequestBody SalesOrderRequest request) {
        log.debug("🔎🔎 CHEGOU NO USECASE, request = {}", request);
        List<CreateSalesOrder.ItemInput> items = request.getItems().stream()
                .map(i -> new CreateSalesOrder.ItemInput(i.getSkuCode(), i.getQuantity()))
                .toList();

        SalesOrder order = createSalesOrder.execute(request.getResellerId(), items);
        if (order == null) {
            throw new IllegalStateException("createSalesOrder retornou null");
        }
        List<SalesOrderResponse.ItemResponse> responseItems = order.getItems().stream()
                .map(i -> new SalesOrderResponse.ItemResponse(i.getSkuCode(), i.getQuantity()))
                .toList();

        return new SalesOrderResponse(order.getId(), responseItems);
    }


}
