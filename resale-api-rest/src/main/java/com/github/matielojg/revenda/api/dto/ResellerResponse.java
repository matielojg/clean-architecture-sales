package com.github.matielojg.revenda.api.dto;

import java.util.UUID;

public record ResellerResponse(UUID id, String name, String email, String cnpj) {

}
