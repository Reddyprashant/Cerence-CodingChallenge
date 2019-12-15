package com.cerence.fibonacciGenerator.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import util.CommonConstants;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public BadRequest(String message){
        super(String.format(CommonConstants.BAD_REQUEST_MESSAGE, message));
        this.message=message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }


}
