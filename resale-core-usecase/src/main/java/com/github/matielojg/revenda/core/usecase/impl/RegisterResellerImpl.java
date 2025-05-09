package com.github.matielojg.revenda.core.usecase.impl;

import com.github.matielojg.revenda.core.domain.entity.Reseller;
import com.github.matielojg.revenda.core.domain.vo.Cnpj;
import com.github.matielojg.revenda.core.domain.vo.EmailAddress;
import com.github.matielojg.revenda.core.gateway.ResellerRepository;
import com.github.matielojg.revenda.core.usecase.RegisterReseller;

import java.util.UUID;

public class RegisterResellerImpl implements RegisterReseller {

    private final ResellerRepository repository;

    public RegisterResellerImpl(ResellerRepository repository) {
        this.repository = repository;
    }

    // sobrecarga para receber dados simples direto do controller
    @Override
    public Reseller execute(Reseller reseller) {
        repository.save(reseller);
        return reseller;
    }

    @SuppressWarnings("unused")
    public void execute(String cnpj, String name, String email) {
        Reseller reseller = new Reseller(
                UUID.randomUUID(),
                new Cnpj(cnpj),
                name,
                new EmailAddress(email)
        );
        execute(reseller);
    }
}
