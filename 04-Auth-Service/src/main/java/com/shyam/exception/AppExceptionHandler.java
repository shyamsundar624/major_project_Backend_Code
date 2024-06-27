package com.shyam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = AuthServiceException.class)
	public ResponseEntity<ExResponse> handelAuthServiceEx(AuthServiceException ae) {
		ExResponse ex = new ExResponse();
		ex.setErrMsg(ae.getMessage());
		ex.setErrCode(ae.getErrCode());

		return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
