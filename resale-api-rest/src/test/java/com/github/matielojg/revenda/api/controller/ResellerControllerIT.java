package com.github.matielojg.revenda.api.controller;

import com.github.matielojg.revenda.api.dto.RegisterResellerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ResellerControllerIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Test
    void shouldRegisterResellerAndReturn201() {

        String url = "http://localhost:" + port + "/resellers";
        RegisterResellerRequest request = new RegisterResellerRequest(
                "45.723.174/0001-10",
                "Loja Integração",
                "integra@teste.com"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterResellerRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Void> response = rest.postForEntity(url, entity, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}