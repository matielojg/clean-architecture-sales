package com.github.matielojg.revenda.core.domain.vo;

import com.github.matielojg.revenda.core.domain.exception.InvalidCnpjException;

public record Cnpj(String value) {

    public Cnpj {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidCnpjException("CNPJ is required");
        }

        value = sanitize(value);

        if (!isValid(value)) {
            throw new InvalidCnpjException("Invalid CNPJ");
        }
    }

    private String sanitize(String value) {
        return value.replaceAll("[^\\d]", "");
    }

    private boolean isValid(String cnpj) {
        if (cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        try {
            int soma1 = 0;
            int soma2 = 0;

            for (int i = 0; i < 12; i++) {
                int digito = Character.getNumericValue(cnpj.charAt(i));
                soma1 += digito * peso1[i];
                soma2 += digito * peso2[i];
            }

            int dig1 = soma1 % 11 < 2 ? 0 : 11 - (soma1 % 11);
            soma2 += dig1 * peso2[12];
            int dig2 = soma2 % 11 < 2 ? 0 : 11 - (soma2 % 11);

            return dig1 == Character.getNumericValue(cnpj.charAt(12)) &&
                    dig2 == Character.getNumericValue(cnpj.charAt(13));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
    }
}