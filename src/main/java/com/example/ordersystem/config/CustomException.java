package com.example.ordersystem.config;

public class CustomException extends RuntimeException {
	private final int statusCode;

	public CustomException(String message) {
		super(message);
		this.statusCode = 405;
	}

	public CustomException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
