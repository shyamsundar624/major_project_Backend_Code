package com.shyam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExHandler {

	@ExceptionHandler(value=ReportsServiceException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceEx(ReportsServiceException rse){
		ErrorResponse resp=new ErrorResponse();
		resp.setErrorCode(rse.getErrorCode());
		resp.setMessage(rse.getMessage());
		
		return new ResponseEntity<>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
