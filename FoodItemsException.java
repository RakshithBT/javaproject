package com.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class FoodItemsException extends Exception {

	public FoodItemsException(String msg)
	{
		super(msg);
	}
}
