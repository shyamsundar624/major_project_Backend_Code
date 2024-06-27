package com.shyam.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportsServiceException extends RuntimeException {

	private String errorCode;

	public ReportsServiceException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
