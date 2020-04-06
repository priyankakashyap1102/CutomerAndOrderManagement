package com.rsys.orderMang.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderIsEmptyException extends RuntimeException {
	
	public OrderIsEmptyException(String exception)
	{
		super(exception);
	}

}
