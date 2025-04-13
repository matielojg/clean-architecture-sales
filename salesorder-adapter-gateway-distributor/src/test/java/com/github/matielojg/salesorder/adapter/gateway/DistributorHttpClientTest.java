package com.github.matielojg.salesorder.adapter.gateway;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrderItem;
import com.github.matielojg.salesorder.core.domain.exception.DistributorUnavailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DistributorHttpClientTest {

    private RestTemplate restTemplate;
    private DistributorHttpClient client;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        client = new DistributorHttpClient(restTemplate, "http://localhost:8082");
    }

    @Test
    void shouldRetryAndFailAfterAllAttempts() {
        SalesOrder order = new SalesOrder(
                UUID.randomUUID(),
                List.of(new SalesOrderItem("SKU123", 500), new SalesOrderItem("SKU999", 600))
        );

        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(Void.class)))
                .thenThrow(new RuntimeException("Simulated distributor error"));

        assertThrows(DistributorUnavailableException.class, () -> client.send(order));

        // Verifica que tentou 3 vezes
        verify(restTemplate, times(3))
                .postForEntity(anyString(), any(HttpEntity.class), eq(Void.class));
    }

    @Test
    void shouldSendSalesOrderSuccessfully() {
        SalesOrder order = new SalesOrder(
                UUID.randomUUID(),
                List.of(new SalesOrderItem("SKU-OK", 1000))
        );

        @SuppressWarnings("unchecked")
        ResponseEntity<Void> mockedResponse = mock(ResponseEntity.class);
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(Void.class)))
                .thenReturn(mockedResponse);

        // execução não deve lançar exceção
        client.send(order);

        // verifica que chamou só 1 vez
        verify(restTemplate, times(1))
                .postForEntity(anyString(), any(HttpEntity.class), eq(Void.class));
    }

}
