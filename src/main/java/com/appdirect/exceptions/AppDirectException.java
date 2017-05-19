package com.appdirect.exceptions;

import com.appdirect.ErrorCode;

public class AppDirectException extends Exception {

	private static final long serialVersionUID = -8484930533839867218L;

	private ErrorCode errorCode;

	public AppDirectException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public AppDirectException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}	

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
