package com.github.matielojg.revenda.core.domain.vo;

import com.github.matielojg.revenda.core.domain.exception.CnpjInvalidoException;

public record Cnpj(String valor) {
    public Cnpj {
        if (!valor.matches("\\d{14}")) {
            throw new CnpjInvalidoException("CNPJ inválido: " + valor);
        }
    }
}
