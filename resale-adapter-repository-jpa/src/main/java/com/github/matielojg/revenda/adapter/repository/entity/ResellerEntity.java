package com.github.matielojg.revenda.adapter.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class ResellerEntity {

    @Id
    private UUID id;
    private String cnpj;
    private String name;
    private String email;

    public ResellerEntity(UUID id, String cnpj, String name, String email) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.email = email;
    }
}
