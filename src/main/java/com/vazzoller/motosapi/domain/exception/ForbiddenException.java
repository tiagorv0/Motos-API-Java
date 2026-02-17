package com.vazzoller.motosapi.domain.exception;

// 403 - Sem permissão
public class ForbiddenException extends BusinessException {
    public ForbiddenException(String message) {
        super(message);
    }
}
