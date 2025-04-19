package com.github.matielojg.salesorder.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.matielojg.salesorder.api.controller.model.SalesOrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SalesOrderControllerValidationIT {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final Logger log = LoggerFactory.getLogger(SalesOrderControllerValidationIT.class);
    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    private String baseUrl() {
        return "http://localhost:" + port + "/api/sales-orders";
    }

    @BeforeEach
    void disableErrorHandler() {
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(@NonNull ClientHttpResponse response) throws IOException {
                return false;
            }
        });
    }
    @Test
    void shouldReturn400ForMissingFields() throws Exception {
        SalesOrderRequest request = new SalesOrderRequest(null, null);

        String json = objectMapper.writeValueAsString(request);

        String url = baseUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpRequest = new HttpEntity<>(json, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpRequest, String.class);
        log.info("🔍 Validation response (missing fields): {}" , response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturn400WhenSalesOrderViolatesBusinessRule() throws Exception {
        SalesOrderRequest request = new SalesOrderRequest(UUID.randomUUID(), List.of(new SalesOrderRequest.ItemRequest("SKU-ERR", 999)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpRequest = new HttpEntity<>(objectMapper.writeValueAsString(request), headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl(), HttpMethod.POST, httpRequest, String.class);
        log.info("🔍 Validation response (business rule): {}" , response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("Sales order must contain at least 1000 units");
    }

}
