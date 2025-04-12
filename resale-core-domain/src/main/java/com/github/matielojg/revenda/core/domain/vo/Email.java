package com.github.matielojg.revenda.core.domain.vo;

import com.github.matielojg.revenda.core.domain.exception.InvalidEmailException;

import java.util.regex.Pattern;

public record Email(String value) {
    private static final Pattern REGEX = Pattern.compile("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$", Pattern.CASE_INSENSITIVE);

    public Email {
        if (value == null || !REGEX.matcher(value).matches()) {
            throw new InvalidEmailException("Invalid Email: " + value);
        }
    }
}
