package com.bankit.application.exceptions;

public class AccountAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountAlreadyExistException() {
		super();
	}

	public AccountAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountAlreadyExistException(String message) {
		super(message);
	}

	public AccountAlreadyExistException(Throwable cause) {
		super(cause);
	}

}
