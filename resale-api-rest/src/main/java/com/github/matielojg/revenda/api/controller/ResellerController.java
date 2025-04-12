package com.github.matielojg.revenda.api.controller;

import com.github.matielojg.revenda.api.dto.RegisterResellerRequest;
import com.github.matielojg.revenda.core.usecase.RegisterReseller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resellers")
@SuppressWarnings("unused")
public class ResellerController {

    private final RegisterReseller registerReseller;

    public ResellerController(RegisterReseller registerReseller) {
        this.registerReseller = registerReseller;
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterResellerRequest request) {
        registerReseller.execute(request.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
