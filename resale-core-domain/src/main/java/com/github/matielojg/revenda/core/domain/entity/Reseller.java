package com.github.matielojg.revenda.core.domain.entity;

import com.github.matielojg.revenda.core.domain.vo.Cnpj;
import com.github.matielojg.revenda.core.domain.vo.EmailAddress;

import java.util.Objects;
import java.util.UUID;

public record Reseller(UUID id, Cnpj cnpj, String corporateName, EmailAddress email) {

    public Reseller {
        Objects.requireNonNull(id, "ID is required");
        Objects.requireNonNull(cnpj, "CNPJ is required");
        Objects.requireNonNull(corporateName, "Corporate name is required");
        Objects.requireNonNull(email, "Email is required");
    }
}
