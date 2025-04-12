package com.github.matielojg.revenda.api.dto;

import com.github.matielojg.revenda.core.domain.entity.Reseller;
import com.github.matielojg.revenda.core.domain.vo.Cnpj;
import com.github.matielojg.revenda.core.domain.vo.Email;

import java.util.UUID;

public record RegisterResellerRequest(String cnpj, String name, String email) {

    public Reseller toDomain() {
        return new Reseller(
                UUID.randomUUID(),
                new Cnpj(this.cnpj),
                this.name,
                new Email(this.email)
        );
    }
}
