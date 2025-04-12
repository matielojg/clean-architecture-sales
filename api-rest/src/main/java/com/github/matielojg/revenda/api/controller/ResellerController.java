package com.github.matielojg.revenda.api.controller;

import com.github.matielojg.revenda.api.dto.RegisterResellerRequest;
import com.github.matielojg.revenda.core.domain.entity.Reseller;
import com.github.matielojg.revenda.core.domain.vo.Cnpj;
import com.github.matielojg.revenda.core.domain.vo.Email;
import com.github.matielojg.revenda.core.usecase.RegisterReseller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/resellers")
public class ResellerController {

    private final RegisterReseller registerReseller;

    public ResellerController(RegisterReseller registerReseller) {
        this.registerReseller = registerReseller;
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterResellerRequest request) {
        Reseller reseller = new Reseller(
                UUID.randomUUID(),
                new Cnpj(request.cnpj()),
                request.name(),
                new Email(request.email())
        );

        registerReseller.execute(reseller);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
