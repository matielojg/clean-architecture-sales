package com.github.matielojg.revenda.core.domain.vo;

import com.github.matielojg.revenda.core.domain.exception.InvalidEmailException;

import java.util.regex.Pattern;

public record EmailAddress(String value) {
    private static final Pattern REGEX = Pattern.compile("^[\\w-.]+@[\\w-]+\\.[a-z]{2,}$", Pattern.CASE_INSENSITIVE);

    public EmailAddress {
        if (value == null || !REGEX.matcher(value).matches()) {
            throw new InvalidEmailException("Invalid Email: " + value);
        }
    }
}
