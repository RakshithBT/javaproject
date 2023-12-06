package com.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.CONFLICT)
public class CategoryException extends Exception{

	public CategoryException(String msg)
	{
		super(msg);
	}

}

