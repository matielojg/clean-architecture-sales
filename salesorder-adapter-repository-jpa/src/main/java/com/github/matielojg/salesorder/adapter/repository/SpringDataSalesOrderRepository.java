package com.github.matielojg.salesorder.adapter.repository;

import com.github.matielojg.salesorder.adapter.repository.entity.SalesOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataSalesOrderRepository extends JpaRepository<SalesOrderEntity, UUID> {
}
