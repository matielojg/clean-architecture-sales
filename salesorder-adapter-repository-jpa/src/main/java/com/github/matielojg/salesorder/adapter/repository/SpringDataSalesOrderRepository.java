package com.github.matielojg.salesorder.adapter.repository;

import com.github.matielojg.salesorder.adapter.repository.entity.OrderStatusEntity;
import com.github.matielojg.salesorder.adapter.repository.entity.SalesOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataSalesOrderRepository extends JpaRepository<SalesOrderEntity, UUID> {


    @Query("SELECT o FROM SalesOrderEntity o LEFT JOIN FETCH o.items WHERE o.status = :status")
    List<SalesOrderEntity> findByStatusWithItems(@Param("status") OrderStatusEntity status);
}
