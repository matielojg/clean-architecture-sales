package com.github.matielojg.revenda.api.config;

import com.github.matielojg.revenda.core.gateway.ResellerRepository;
import com.github.matielojg.revenda.core.usecase.RegisterReseller;
import com.github.matielojg.revenda.core.usecase.impl.RegisterResellerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public RegisterReseller registerReseller(ResellerRepository repository) {
        return new RegisterResellerImpl(repository);
    }
}
