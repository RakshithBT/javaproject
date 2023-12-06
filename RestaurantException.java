package com.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class RestaurantException extends Exception {
	
	public RestaurantException(String msg)
	{
		super(msg);
	}

}
