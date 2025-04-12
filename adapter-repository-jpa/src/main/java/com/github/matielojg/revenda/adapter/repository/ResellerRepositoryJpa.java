package com.github.matielojg.revenda.adapter.repository;

import com.github.matielojg.revenda.adapter.repository.mapper.ResellerMapper;
import com.github.matielojg.revenda.core.domain.entity.Reseller;
import com.github.matielojg.revenda.core.gateway.ResellerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ResellerRepositoryJpa implements ResellerRepository {

    private final ResellerRepositorySpringData repository;

    public ResellerRepositoryJpa(ResellerRepositorySpringData repository) {
        this.repository = repository;
    }

    @Override
    public void save(Reseller reseller) {
        repository.save(ResellerMapper.toEntity(reseller));
    }
}
