package com.shyam.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartServiceException extends RuntimeException{

	private String erroCode;

	public CartServiceException(String message, String errorCode) {
		super(message);
		this.erroCode = errorCode;
	}
}
