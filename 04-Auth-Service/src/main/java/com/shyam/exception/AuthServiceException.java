package com.shyam.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthServiceException extends RuntimeException{

	private String errCode;
	public AuthServiceException(String msg,String errCode) {
		super(msg);
		this.errCode=errCode;
		// TODO Auto-generated constructor stub
	}
	public AuthServiceException() {
		// TODO Auto-generated constructor stub
	}

	

	
}
