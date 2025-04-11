package com.github.matielojg.revenda.core.domain.exception;

public class CnpjInvalidoException extends RuntimeException {
    public CnpjInvalidoException(String mensagem) {
        super(mensagem);
    }
}
