package com.github.matielojg.salesorder.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest.ItemRequest;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(MockDistributorController.class)
class SalesOrderControllerIT {

    @LocalServerPort
    private final int port;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ObjectMapper objectMapper;

    SalesOrderControllerIT(int port) {
        this.port = port;
    }

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

        String url = "http://localhost:" + port + "/api/sales-orders";
        ResponseEntity<SalesOrderResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpRequest,
                SalesOrderResponse.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).orderId()).isNotNull();
        List<SalesOrderResponse.ItemResponse> items = response.getBody().items();
        assertThat(items).hasSize(2);

    }

    private ItemRequest createItem(String sku, int qty) {
        ItemRequest item = new ItemRequest();
        item.setSkuCode(sku);
        item.setQuantity(qty);
        return item;
    }

    @Test
    void shouldReturnEmptyListWhenNoSalesOrderWithGivenStatus() {
        ResponseEntity<SalesOrderResponse[]> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/sales-orders?status=SENT",
                SalesOrderResponse[].class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEmpty();
    }

}
