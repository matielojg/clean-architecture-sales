package com.github.matielojg.revenda.core.usecase.impl;

import com.github.matielojg.revenda.core.domain.entity.Reseller;
import com.github.matielojg.revenda.core.gateway.ResellerRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class RegisterResellerImplTest {

    @Test
    void shouldCallSaveWhenResellerIsValid() {
        ResellerRepository repository = mock(ResellerRepository.class);
        RegisterResellerImpl usecase = new RegisterResellerImpl(repository);

        Reseller reseller = mock(Reseller.class);
        usecase.execute(reseller);

        verify(repository, times(1)).save(reseller);
    }
}
