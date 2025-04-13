package com.github.matielojg.salesorder.adapter.repository;

import com.github.matielojg.salesorder.adapter.repository.entity.OrderStatusEntity;
import com.github.matielojg.salesorder.adapter.repository.entity.SalesOrderEntity;
import com.github.matielojg.salesorder.adapter.repository.entity.SalesOrderItemEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class SalesOrderRepositoryJpaIT {

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void shouldUpdateSalesOrderStatus() {
        UUID orderId = UUID.randomUUID();

        SalesOrderEntity entity = new SalesOrderEntity();
        entity.setId(orderId);
        entity.setResellerId(UUID.randomUUID());
        entity.setStatus(OrderStatusEntity.FAILED);

        SalesOrderItemEntity item = new SalesOrderItemEntity();
        item.setSkuCode("SKU123");
        item.setQuantity(10);
        item.setSalesOrder(entity);

        entity.setItems(List.of(item));
        em.persist(entity);
        em.flush();
        em.clear();

        em.createQuery("UPDATE SalesOrderEntity o SET o.status = :status WHERE o.id = :id")
                .setParameter("status", OrderStatusEntity.SENT)
                .setParameter("id", orderId)
                .executeUpdate();

        em.flush();
        em.clear();

        SalesOrderEntity updated = em.find(SalesOrderEntity.class, orderId);
        System.out.println("🔎 Status after update: " + updated.getStatus()); // debug temporário

        assertThat(updated.getStatus()).isEqualTo(OrderStatusEntity.SENT);
    }

    @Test
    @Transactional
    void shouldFindOrdersByStatus() {
        SalesOrderEntity sent = new SalesOrderEntity();
        sent.setId(UUID.randomUUID());
        sent.setResellerId(UUID.randomUUID());
        sent.setStatus(OrderStatusEntity.SENT);

        SalesOrderEntity failed = new SalesOrderEntity();
        failed.setId(UUID.randomUUID());
        failed.setResellerId(UUID.randomUUID());
        failed.setStatus(OrderStatusEntity.FAILED);

        em.persist(sent);
        em.persist(failed);

        List<SalesOrderEntity> results = em.createQuery(
                        "SELECT o FROM SalesOrderEntity o WHERE o.status = :status", SalesOrderEntity.class)
                .setParameter("status", OrderStatusEntity.FAILED)
                .getResultList();

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getStatus()).isEqualTo(OrderStatusEntity.FAILED);
    }
}
