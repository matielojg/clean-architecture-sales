package com.github.matielojg.salesorder.adapter.gateway;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.exception.DistributorUnavailableException;
import com.github.matielojg.salesorder.core.gateway.DistributorGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class DistributorHttpClient implements DistributorGateway {

    private static final Logger log = LoggerFactory.getLogger(DistributorHttpClient.class);

    private final RestTemplate restTemplate;
    private final String distributorUrl;

    public DistributorHttpClient(RestTemplate restTemplate, String distributorUrl) {
        this.restTemplate = restTemplate;
        this.distributorUrl = distributorUrl;
    }

    @Override
    public void send(SalesOrder order) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = new HashMap<>();
            body.put("orderId", order.getId());
            body.put("resellerId", order.getResellerId());
            body.put("items", order.getItems());

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<Void> response = restTemplate.postForEntity(distributorUrl + "/api/orders", request, Void.class);

            log.info("Sales order {} sent successfully to distributor. Status: {}", order.getId(), response.getStatusCode());

        } catch (Exception ex) {
            String errorMessage = String.format(
                    "Failed to send sales order %s to distributor: %s",
                    order.getId(), ex.getMessage()
            );
            throw new DistributorUnavailableException(errorMessage, ex); // Relança com contexto
        }
    }
}
