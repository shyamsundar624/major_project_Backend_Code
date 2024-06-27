package com.shyam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExHandler {

public ResponseEntity<ErrorReponse> hadleProductServiceEx(CartServiceException ce){
	ErrorReponse resp=new ErrorReponse();
	resp.setErrorCode(ce.getErroCode());
	resp.setMessage(ce.getMessage());
	
	return new ResponseEntity<>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
}
}
