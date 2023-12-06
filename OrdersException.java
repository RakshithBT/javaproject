package com.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class OrdersException extends Exception {
	
	public OrdersException(String msg)
	{
		super(msg);
	}

}
