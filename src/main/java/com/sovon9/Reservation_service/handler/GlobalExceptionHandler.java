package com.sovon9.Reservation_service.handler;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sovon9.Reservation_service.exception.PMSErrorResponse;
import com.sovon9.Reservation_service.exception.PMSException;

@ControllerAdvice
public class GlobalExceptionHandler
{
	@ExceptionHandler
	public ResponseEntity<PMSErrorResponse> handlePMSException(PMSException exception)
	{
		PMSErrorResponse errorResponse = new PMSErrorResponse();
		errorResponse.setStatus(exception.getStatus());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<PMSErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception)
	{
		PMSErrorResponse errorResponse = new PMSErrorResponse();
		errorResponse.setStatus(HttpStatus.NOT_FOUND.toString());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<PMSErrorResponse> handleException(Exception exception)
	{
		PMSErrorResponse errorResponse = new PMSErrorResponse();
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
