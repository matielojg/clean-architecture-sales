package com.github.matielojg.salesorder.adapter.gateway;

import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.exception.DistributorUnavailableException;
import com.github.matielojg.salesorder.core.gateway.DistributorGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class DistributorHttpClient implements DistributorGateway {

    private static final Logger log = LoggerFactory.getLogger(DistributorHttpClient.class);

    private final RestTemplate restTemplate;
    private final String distributorUrl;
    private final RetryTemplate retryTemplate;

    public DistributorHttpClient(RestTemplate restTemplate,
                                 @Value("${distributor.url}") String distributorUrl) {
        this.restTemplate = restTemplate;
        this.distributorUrl = distributorUrl;

        this.retryTemplate = new RetryTemplate();

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(1000);
        backOffPolicy.setMultiplier(2);
        backOffPolicy.setMaxInterval(5000);

        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
    }

    @Override
    public void send(SalesOrder order) {
        retryTemplate.execute(
                context -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    Map<String, Object> body = new HashMap<>();
                    body.put("orderId", order.getId());
                    body.put("resellerId", order.getResellerId());
                    body.put("items", order.getItems());

                    HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

                    ResponseEntity<Void> response = restTemplate.postForEntity(
                            distributorUrl + "/api/orders", request, Void.class);

                    log.info("Sales order {} sent successfully to distributor. Status: {}", order.getId(), response.getStatusCode());

                    return null;
                },
                context -> {
                    Throwable lastThrowable = context.getLastThrowable();
                    throw new DistributorUnavailableException(
                            String.format("Failed to send sales order %s to distributor after retries", order.getId()),
                            lastThrowable != null ? lastThrowable : new RuntimeException("Unknown error")
                    );
                }
        );
    }
}