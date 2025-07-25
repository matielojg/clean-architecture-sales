package com.github.matielojg.revenda.core.domain.exception;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String mensagem) {
        super(mensagem);
    }
}
