package com.shyam.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {

	private Integer statusCode;
	private String message;
	private T Date;
}
