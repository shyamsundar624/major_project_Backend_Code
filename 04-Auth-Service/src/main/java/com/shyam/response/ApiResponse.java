package com.shyam.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

	private String message;
	private Integer status;
	private T data;

}
