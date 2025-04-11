package com.github.matielojg.revenda.core.usecase.impl;

import com.github.matielojg.revenda.core.domain.entity.Reseller;
import com.github.matielojg.revenda.core.usecase.RegisterReseller;
import com.github.matielojg.revenda.core.gateway.ResellerRepository;

import java.util.Objects;

public class RegisterResellerImpl implements RegisterReseller {

    private final ResellerRepository repository;

    public RegisterResellerImpl(ResellerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Reseller reseller) {
        Objects.requireNonNull(reseller, "Revenda não pode ser nula");
        repository.save(reseller);
    }
}
