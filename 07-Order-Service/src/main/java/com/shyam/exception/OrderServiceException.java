package com.shyam.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderServiceException extends RuntimeException{

	private String errCode;
	
	public OrderServiceException(String msg,String errCode) {
		super(msg);
		this.errCode=errCode;
	}
	public OrderServiceException() {
		
	}
}
