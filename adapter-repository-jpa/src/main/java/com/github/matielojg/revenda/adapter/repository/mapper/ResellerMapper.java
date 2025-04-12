package com.github.matielojg.revenda.adapter.repository.mapper;

import com.github.matielojg.revenda.adapter.repository.entity.ResellerEntity;
import com.github.matielojg.revenda.core.domain.entity.Reseller;

public class ResellerMapper {

    private ResellerMapper() {
        throw new IllegalStateException("Utility class");   }

    public static ResellerEntity toEntity(Reseller reseller) {
        return new ResellerEntity(
                reseller.id(), // vem do domínio
                reseller.cnpj().value(),
                reseller.corporateName(),
                reseller.email().value()
        );
    }
}
