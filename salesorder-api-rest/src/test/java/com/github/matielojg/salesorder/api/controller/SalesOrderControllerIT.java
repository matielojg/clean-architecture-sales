package com.github.matielojg.salesorder.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest.ItemRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SalesOrderController.class)
class SalesOrderControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateSalesOrderSuccessfully() throws Exception {
        SalesOrderRequest request = new SalesOrderRequest();
        request.setResellerId(UUID.randomUUID());
        request.setItems(List.of(
                createItem("SKU-A", 500),
                createItem("SKU-B", 600)
        ));

        mockMvc.perform(post("/api/sales-orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").exists())
                .andExpect(jsonPath("$.items.length()").value(2));
    }

    private ItemRequest createItem(String sku, int qty) {
        ItemRequest item = new ItemRequest();
        item.setSkuCode(sku);
        item.setQuantity(qty);
        return item;
    }
}
