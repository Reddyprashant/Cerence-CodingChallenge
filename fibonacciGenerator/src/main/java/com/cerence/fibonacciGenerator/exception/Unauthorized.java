package com.cerence.fibonacciGenerator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import util.CommonConstants;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class Unauthorized extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;
    private HttpStatus status = HttpStatus.UNAUTHORIZED;

    public Unauthorized(String message) {
        super(String.format(CommonConstants.TOKEN_EXPIRED, message));
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }


}
