package com.shyam.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponse {

	private String errCode;
	private String errMsg;

	public ExceptionResponse(String errMsg, String errCode) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
}
