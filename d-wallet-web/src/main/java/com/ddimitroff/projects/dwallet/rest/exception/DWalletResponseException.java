package com.ddimitroff.projects.dwallet.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DWalletResponseException extends Exception {

	private static final long serialVersionUID = 1L;

	public DWalletResponseException() {
		super();
	}

	public DWalletResponseException(String message) {
		super(message);
	}

	public DWalletResponseException(Throwable t) {
		super(t);
	}

	public DWalletResponseException(String message, Throwable t) {
		super(message, t);
	}

}
