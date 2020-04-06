package com.rsys.orderMang.ExceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(OrderIsEmptyException.class)
	public final ResponseEntity<ErrorDetails> handleOrderIsEmptyException(OrderIsEmptyException ex,WebRequest request)
	{
		ErrorDetails errorDetails=new ErrorDetails(new Date(), ex.getMessage(),
		        request.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	
	@ExceptionHandler(CustomerIsEmptyException.class)
	public final ResponseEntity<ErrorDetails> handleCustomerIsEmptyException(CustomerIsEmptyException ex,WebRequest request)
	{
		ErrorDetails errorDetails=new ErrorDetails(new Date(), ex.getMessage(),
		        request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(ProductNotFoundException ex,WebRequest request)
	{
		ErrorDetails errorDetails=new ErrorDetails(new Date(), ex.getMessage(),
		        request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	 @ExceptionHandler(Exception.class)
	 public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
	   ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	       request.getDescription(false));
	   return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 

}
