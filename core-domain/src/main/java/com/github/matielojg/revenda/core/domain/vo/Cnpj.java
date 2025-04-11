package com.github.matielojg.revenda.core.domain.vo;

import com.github.matielojg.revenda.core.domain.exception.InvalidCnpjException;

public record Cnpj(String value) {
    public Cnpj {
        if (!value.matches("\\d{14}")) {
            throw new InvalidCnpjException("Invalid CNPJ : " + value);
        }
    }
}
