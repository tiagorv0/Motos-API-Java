package com.vazzoller.motosapi.domain.exception;

// 400 - Regra de negócio violada
public class BadRequestException extends BusinessException {
    public BadRequestException(String message) {
        super(message);
    }
}
