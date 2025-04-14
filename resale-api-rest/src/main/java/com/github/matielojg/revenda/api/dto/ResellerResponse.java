package com.github.matielojg.revenda.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Response with reseller details")
public record ResellerResponse(

        @Schema(description = "Reseller unique identifier", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID id,

        @Schema(description = "Corporate name", example = "Bar do Zé")
        String name,

        @Schema(description = "Email address", example = "ze@bardoze.com")
        String email,

        @Schema(description = "CNPJ number", example = "12345678000199")
        String cnpj

) {}
