package com.github.matielojg.revenda.api.controller;

import com.github.matielojg.revenda.api.dto.RegisterResellerRequest;
import com.github.matielojg.revenda.api.dto.ResellerResponse;
import com.github.matielojg.revenda.api.openapi.ResellerApi;
import com.github.matielojg.revenda.core.domain.entity.Reseller;
import com.github.matielojg.revenda.core.usecase.RegisterReseller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resellers")
@SuppressWarnings("unused")
public class ResellerController implements ResellerApi {

    private final RegisterReseller registerReseller;

    public ResellerController(RegisterReseller registerReseller) {
        this.registerReseller = registerReseller;
    }

    @PostMapping
    public ResponseEntity<ResellerResponse> register(@Valid @RequestBody RegisterResellerRequest request) {
        Reseller reseller = registerReseller.execute(request.toDomain());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResellerResponse(
                        reseller.id(),
                        reseller.corporateName(),
                        reseller.email().value(),
                        reseller.cnpj().value()
                )
        );
    }

}
