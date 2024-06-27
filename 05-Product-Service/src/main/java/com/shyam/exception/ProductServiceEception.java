package com.shyam.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductServiceEception extends RuntimeException{

	private String errorCode;
	
	public ProductServiceEception(String msg,String erroCode) {
		super(msg);
		this.errorCode=erroCode;
	}
}
