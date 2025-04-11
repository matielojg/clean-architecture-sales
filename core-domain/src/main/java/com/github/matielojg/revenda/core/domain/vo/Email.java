package com.github.matielojg.revenda.core.domain.vo;

import com.github.matielojg.revenda.core.domain.exception.EmailInvalidoException;

import java.util.regex.Pattern;

public record Email(String valor) {
    private static final Pattern REGEX = Pattern.compile("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$", Pattern.CASE_INSENSITIVE);

    public Email {
        if (valor == null || !REGEX.matcher(valor).matches()) {
            throw new EmailInvalidoException("Email inválido: " + valor);
        }
    }
}
