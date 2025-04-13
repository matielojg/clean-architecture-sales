package com.github.matielojg.salesorder.adapter.repository;

import com.github.matielojg.salesorder.adapter.repository.mapper.SalesOrderMapper;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class SalesOrderRepositoryJpa implements SalesOrderRepository {

    private final SpringDataSalesOrderRepository repository;

    public SalesOrderRepositoryJpa(SpringDataSalesOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void save(SalesOrder order) {
        repository.save(SalesOrderMapper.toEntity(order));
    }
}
