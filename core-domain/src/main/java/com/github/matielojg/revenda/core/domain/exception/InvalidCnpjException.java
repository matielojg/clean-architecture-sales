package com.github.matielojg.revenda.core.domain.exception;

public class InvalidCnpjException extends RuntimeException {
    public InvalidCnpjException(String mensagem) {
        super(mensagem);
    }
}
