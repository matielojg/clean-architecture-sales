package com.github.matielojg.revenda.core.usecase.impl;

import com.github.matielojg.revenda.core.domain.entity.Reseller;
import com.github.matielojg.revenda.core.gateway.ResellerRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RegisterResellerImplTest {

    @Test
    void shouldCallSaveWhenResellerIsValid() {
        // seguindo o padrao de escrita AAA
        ResellerRepository repository = mock(ResellerRepository.class);
        RegisterResellerImpl useCase = new RegisterResellerImpl(repository);
        Reseller reseller = mock(Reseller.class);

        useCase.execute(reseller);

        verify(repository, times(1)).save(reseller);
    }
}
