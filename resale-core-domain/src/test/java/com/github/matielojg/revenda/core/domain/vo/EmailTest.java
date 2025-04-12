package com.github.matielojg.revenda.core.domain.vo;

import com.github.matielojg.revenda.core.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateValidEmail() {
        Email email = new Email("valid@email.com");
        assertEquals("valid@email.com", email.value());
    }

    @Test
    void shouldThrowExceptionForInvalidEmail() {
        assertThrows(InvalidEmailException.class, () -> new Email("invalid"));
    }
    @Test
    void shouldThrowExceptionForNullEmail() {
        assertThrows(InvalidEmailException.class, () -> new Email(null));
    }

    @Test
    void shouldThrowExceptionForEmptyEmail() {
        assertThrows(InvalidEmailException.class, () -> new Email(""));
    }

    @Test
    void shouldThrowExceptionForEmailWithoutDomain() {
        assertThrows(InvalidEmailException.class, () -> new Email("user@"));
    }

    @Test
    void shouldThrowExceptionForEmailWithSpaces() {
        assertThrows(InvalidEmailException.class, () -> new Email("invalid email@email.com"));
    }
}
