package com.github.matielojg.revenda.core.gateway;

import com.github.matielojg.revenda.core.domain.entity.Reseller;

public interface ResellerRepository {
    void save(Reseller reseller);
}
