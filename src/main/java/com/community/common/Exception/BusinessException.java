package com.community.common.Exception;

public class BusinessException extends RuntimeException{
    private final int code=400;
    public BusinessException(String message) {
        super(message);
    }
}
