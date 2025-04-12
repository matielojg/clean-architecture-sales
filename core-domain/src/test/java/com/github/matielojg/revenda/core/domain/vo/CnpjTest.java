package com.github.matielojg.revenda.core.domain.vo;

import com.github.matielojg.revenda.core.domain.exception.InvalidCnpjException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CnpjTest {

    @Test
    void shouldCreateValidCnpj() {
        String valid = "45.723.174/0001-10";
        Cnpj cnpj = new Cnpj(valid);
        assertEquals("45723174000110", cnpj.value());
    }

    @Test
    void shouldThrowExceptionWhenCnpjIsInvalid() {
        String invalid = "12.345.678/0001-XX";
        assertThrows(InvalidCnpjException.class, () -> new Cnpj(invalid));
    }

    @Test
    void shouldThrowExceptionWhenCnpjIsNull() {
        assertThrows(InvalidCnpjException.class, () -> new Cnpj(null));
    }

    @Test
    void shouldThrowExceptionWhenCnpjIsEmpty() {
        assertThrows(InvalidCnpjException.class, () -> new Cnpj(""));
    }
    @Test
    void shouldThrowExceptionWhenCnpjHasLessThan14Digits() {
        assertThrows(InvalidCnpjException.class, () -> new Cnpj("1234567890001")); // 13 dígitos
    }

    @Test
    void shouldThrowExceptionWhenCnpjHasMoreThan14Digits() {
        assertThrows(InvalidCnpjException.class, () -> new Cnpj("123456789000123")); // 15 dígitos
    }

}
