package com.github.matielojg.salesorder.api.openapi;

import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderResponse;
import com.github.matielojg.salesorder.core.domain.vo.SalesOrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sales Orders", description = "Endpoints for creating and listing sales orders")
public interface SalesOrderApi {

    @Operation(summary = "Create a new sales order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sales order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<SalesOrderResponse> create(@Valid @RequestBody SalesOrderRequest request);

    @Operation(summary = "List sales orders filtered by status (optional)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of sales orders returned"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    List<SalesOrderResponse> listByStatus(@RequestParam(required = false) SalesOrderStatus status);
}