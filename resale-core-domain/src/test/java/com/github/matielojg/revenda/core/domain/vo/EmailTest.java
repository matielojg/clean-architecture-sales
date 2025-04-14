package com.github.matielojg.revenda.core.domain.vo;

import com.github.matielojg.revenda.core.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateValidEmail() {
        EmailAddress email = new EmailAddress("valid@email.com");
        assertEquals("valid@email.com", email.value());
    }

    @Test
    void shouldThrowExceptionForInvalidEmail() {
        assertThrows(InvalidEmailException.class, () -> new EmailAddress("invalid"));
    }
    @Test
    void shouldThrowExceptionForNullEmail() {
        assertThrows(InvalidEmailException.class, () -> new EmailAddress(null));
    }

    @Test
    void shouldThrowExceptionForEmptyEmail() {
        assertThrows(InvalidEmailException.class, () -> new EmailAddress(""));
    }

    @Test
    void shouldThrowExceptionForEmailWithoutDomain() {
        assertThrows(InvalidEmailException.class, () -> new EmailAddress("user@"));
    }

    @Test
    void shouldThrowExceptionForEmailWithSpaces() {
        assertThrows(InvalidEmailException.class, () -> new EmailAddress("invalid email@email.com"));
    }
}
