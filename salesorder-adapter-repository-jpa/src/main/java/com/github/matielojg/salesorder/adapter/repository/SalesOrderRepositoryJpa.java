package com.github.matielojg.salesorder.adapter.repository;

import com.github.matielojg.salesorder.adapter.repository.entity.OrderStatusEntity;
import com.github.matielojg.salesorder.adapter.repository.entity.SalesOrderEntity;
import com.github.matielojg.salesorder.adapter.repository.mapper.SalesOrderMapper;
import com.github.matielojg.salesorder.core.domain.entity.SalesOrder;
import com.github.matielojg.salesorder.core.domain.vo.SalesOrderStatus;
import com.github.matielojg.salesorder.core.gateway.SalesOrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SalesOrderRepositoryJpa implements SalesOrderRepository {

    private final SpringDataSalesOrderRepository repository;
    @PersistenceContext
    private EntityManager em;

    public SalesOrderRepositoryJpa(SpringDataSalesOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void save(SalesOrder order) {
        repository.save(SalesOrderMapper.toEntity(order));
    }

    @Override
    public List<SalesOrder> findByStatus(SalesOrderStatus status) {
        return em.createQuery("SELECT o FROM SalesOrderEntity o WHERE o.status = :status", SalesOrderEntity.class)
                .setParameter("status", OrderStatusEntity.valueOf(status.name()))
                .getResultList()
                .stream()
                .map(SalesOrderMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void updateStatus(UUID orderId, SalesOrderStatus status) {
        em.createQuery("UPDATE SalesOrderEntity o SET o.status = :status WHERE o.id = :id")
                .setParameter("status", OrderStatusEntity.valueOf(status.name()))
                .setParameter("id", orderId)
                .executeUpdate();
    }
}
