package com.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {
	
		@ExceptionHandler(CartException.class)
		public ResponseEntity<?> cartException(CartException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
		}
	
		@ExceptionHandler(CustomerException.class)
		public ResponseEntity<?> customerException(CustomerException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
		}
		
		@ExceptionHandler(FoodItemsException.class)
		public ResponseEntity<?> foodItemsException(FoodItemsException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
		}
		@ExceptionHandler(OrdersException.class)
		public ResponseEntity<?> ordersException(OrdersException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(RestaurantException.class)
		public ResponseEntity<?> restaurantException(RestaurantException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
