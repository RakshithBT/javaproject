package com.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class UserNotAvailableException extends Exception
{

	public UserNotAvailableException(String msg)
	{
		super(msg);
	}
}