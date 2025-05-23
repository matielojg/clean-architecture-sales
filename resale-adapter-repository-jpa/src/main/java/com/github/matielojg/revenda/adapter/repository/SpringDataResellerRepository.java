package com.github.matielojg.revenda.adapter.repository;

import com.github.matielojg.revenda.adapter.repository.entity.ResellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataResellerRepository extends JpaRepository<ResellerEntity, UUID> {
}
