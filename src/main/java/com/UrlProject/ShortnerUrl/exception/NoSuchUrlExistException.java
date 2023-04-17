package com.UrlProject.ShortnerUrl.exception;

public class NoSuchUrlExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public NoSuchUrlExistException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
