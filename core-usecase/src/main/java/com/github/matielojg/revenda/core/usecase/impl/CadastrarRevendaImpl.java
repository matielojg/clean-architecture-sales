package com.github.matielojg.revenda.core.usecase.impl;

import com.github.matielojg.revenda.core.domain.entity.Revenda;
import com.github.matielojg.revenda.core.usecase.CadastrarRevenda;
import com.github.matielojg.revenda.core.gateway.RevendaRepository;

import java.util.Objects;

public class CadastrarRevendaImpl implements CadastrarRevenda {

    private final RevendaRepository repository;

    public CadastrarRevendaImpl(RevendaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executar(Revenda revenda) {
        Objects.requireNonNull(revenda, "Revenda não pode ser nula");
        repository.salvar(revenda);
    }
}
