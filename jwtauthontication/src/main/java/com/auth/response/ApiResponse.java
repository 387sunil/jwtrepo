package com.auth.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse<T> {
	
	private boolean success;
	
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;
	
	

	public ApiResponse(boolean success, String message, T data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public ApiResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

}
