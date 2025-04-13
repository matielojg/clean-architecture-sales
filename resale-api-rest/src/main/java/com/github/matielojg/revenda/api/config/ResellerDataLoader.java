package com.github.matielojg.revenda.api.config;


import com.github.matielojg.revenda.core.domain.entity.Reseller;
import com.github.matielojg.revenda.core.domain.vo.Cnpj;
import com.github.matielojg.revenda.core.domain.vo.Email;
import com.github.matielojg.revenda.core.gateway.ResellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
@Profile({"dev", "docker"})
public class ResellerDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ResellerDataLoader.class);

    private final ResellerRepository repository;

    public ResellerDataLoader(ResellerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        int quantidade = 10;
        List<String> cnpjsValidos = List.of(
                "45.723.174/0001-10", "88.872.511/0001-44","63.929.353/0001-90","40.676.509/0001-73","25.423.312/0001-39",
                "18.456.555/0001-08","97.255.095/0001-26","00.134.286/0001-06","27.293.663/0001-06","16.692.353/0001-30","76.337.924/0001-40","46.314.626/0001-73",
                "44.869.645/0001-30","16.270.320/0001-00","33.036.554/0001-06"
        );

        IntStream.rangeClosed(1, quantidade).forEach(i -> {
            UUID id = UUID.randomUUID();
            String rawCnpj = cnpjsValidos.get(i % cnpjsValidos.size());
            String rawEmail = "reseller" + i + "@example.com";

            Reseller reseller = new Reseller(
                    id,
                    new Cnpj(rawCnpj),
                    "Reseller " + i,
                    new Email(rawEmail)
            );

            repository.save(reseller);
            log.info("✅ Reseller cadastrado: {}", reseller.email().value());
        });
    }

}