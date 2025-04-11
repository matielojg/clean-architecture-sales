package com.github.matielojg.revenda.core.domain.entity;

import com.github.matielojg.revenda.core.domain.vo.Cnpj;
import com.github.matielojg.revenda.core.domain.vo.Email;

import java.util.Objects;

public record Revenda(Cnpj cnpj, String razaoSocial, Email email) {

    public Revenda {
        Objects.requireNonNull(cnpj, "CNPJ é obrigatório");
        Objects.requireNonNull(razaoSocial, "Razão Social é obrigatória");
        Objects.requireNonNull(email, "Email é obrigatório");
    }
}
