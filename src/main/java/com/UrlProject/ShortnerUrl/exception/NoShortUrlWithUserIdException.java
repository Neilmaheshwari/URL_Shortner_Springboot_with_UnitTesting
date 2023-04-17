package com.UrlProject.ShortnerUrl.exception;

public class NoShortUrlWithUserIdException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	private String message;

	public NoShortUrlWithUserIdException(String message) {
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
