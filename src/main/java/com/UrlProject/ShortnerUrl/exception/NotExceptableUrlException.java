package com.UrlProject.ShortnerUrl.exception;

public class NotExceptableUrlException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotExceptableUrlException(String message) {
		super();
		this.message = message;
	}

}
