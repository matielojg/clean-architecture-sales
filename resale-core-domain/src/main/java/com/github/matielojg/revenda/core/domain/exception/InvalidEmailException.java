package com.github.matielojg.revenda.core.domain.exception;

@SuppressWarnings("unused")
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String mensagem) {
        super(mensagem);
    }
}
