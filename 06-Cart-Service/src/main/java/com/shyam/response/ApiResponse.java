package com.shyam.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {

	private String message;
	private Integer statusCode;
	private T data;
}
