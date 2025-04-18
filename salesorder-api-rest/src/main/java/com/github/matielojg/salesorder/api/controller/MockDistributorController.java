package com.github.matielojg.salesorder.api.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class MockDistributorController {

    private static final Logger log = LoggerFactory.getLogger(MockDistributorController.class);

    @PostMapping
    public ResponseEntity<Void> receiveOrder(@RequestBody Map<String, Object> body) {
        log.info("Received order mock: {}", body);
        return ResponseEntity.ok().build();
    }
}
