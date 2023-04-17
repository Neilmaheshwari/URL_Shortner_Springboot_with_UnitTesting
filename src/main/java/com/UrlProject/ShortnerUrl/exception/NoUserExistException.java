package com.UrlProject.ShortnerUrl.exception;

public class NoUserExistException  extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String message;

	
	
	public NoUserExistException(String message) {
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
