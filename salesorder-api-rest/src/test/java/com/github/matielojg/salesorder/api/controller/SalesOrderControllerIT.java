package com.github.matielojg.salesorder.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest.ItemRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(MockDistributorController.class)
class SalesOrderControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void shouldCreateSalesOrderSuccessfully() throws Exception {
        SalesOrderRequest request = new SalesOrderRequest();
        request.setResellerId(UUID.randomUUID());
        request.setItems(List.of(
                createItem("SKU-001", 600),
                createItem("SKU-002", 500)
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpRequest = new HttpEntity<>(
                objectMapper.writeValueAsString(request), headers
        );

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/sales-orders",
                httpRequest,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("orderId");
    }

    private ItemRequest createItem(String sku, int qty) {
        ItemRequest item = new ItemRequest();
        item.setSkuCode(sku);
        item.setQuantity(qty);
        return item;
    }
}
