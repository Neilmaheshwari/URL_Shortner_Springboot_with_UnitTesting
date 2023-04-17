package com.UrlProject.ShortnerUrl.exception;

public class UrlNotValidAnymoreException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UrlNotValidAnymoreException(String message) {
		super();
		this.message = message;
	}
}
