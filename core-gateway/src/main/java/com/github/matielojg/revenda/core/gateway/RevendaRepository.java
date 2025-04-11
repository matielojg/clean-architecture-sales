package com.github.matielojg.revenda.core.gateway;

import com.github.matielojg.revenda.core.domain.entity.Revenda;

public interface RevendaRepository {
    void salvar(Revenda revenda);
}
