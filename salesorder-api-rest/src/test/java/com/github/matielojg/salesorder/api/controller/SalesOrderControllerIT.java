package com.github.matielojg.salesorder.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest.ItemRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(MockDistributorController.class)
class SalesOrderControllerIT {

    private final RestTemplate restTemplate = new RestTemplate();
    @LocalServerPort
    private int port;
    @Autowired
    private ObjectMapper objectMapper;

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

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    "http://localhost:" + port + "/api/sales-orders",
                    httpRequest,
                    String.class
            );
            System.out.println("✅ Response body: " + response.getBody());

        } catch (HttpServerErrorException e) {
            System.out.println("❌ 500 ERROR BODY:");
            System.out.println(e.getResponseBodyAsString()); // <== aqui vai vir a exceção real
            throw e;
        }
    }

    private ItemRequest createItem(String sku, int qty) {
        ItemRequest item = new ItemRequest();
        item.setSkuCode(sku);
        item.setQuantity(qty);
        return item;
    }
}
