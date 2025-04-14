package com.github.matielojg.revenda.api.dto;

import com.github.matielojg.revenda.core.domain.entity.Reseller;
import com.github.matielojg.revenda.core.domain.vo.Cnpj;
import com.github.matielojg.revenda.core.domain.vo.EmailAddress;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record RegisterResellerRequest(
        @Schema(description = "CNPJ", example = "45723174000110")
        @NotBlank
        String cnpj,

        @Schema(description = "Corporate name", example = "Bar da Osvaldo")
        @NotBlank
        String name,

        @NotBlank
        @Email
        @Schema(description = "Email", example = "osvaldo@exemplo.com")
        String email
) {

    public Reseller toDomain() {
        return new Reseller(
                UUID.randomUUID(),
                new Cnpj(this.cnpj),
                this.name,
                new EmailAddress(this.email)
        );
    }
}
