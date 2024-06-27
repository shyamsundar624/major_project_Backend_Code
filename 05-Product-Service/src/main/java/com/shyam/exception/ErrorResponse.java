package com.shyam.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ErrorResponse {

	private String message;
	private String errorCode;
}
