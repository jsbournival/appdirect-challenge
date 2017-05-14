package com.appdirect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_ABSENT)
public class AppDirectResponse {

	private boolean success;
	private String message;
	private ErrorCode errorCode;
	private String accountIdentifier;

	private AppDirectResponse(boolean success, String message, ErrorCode errorCode, String accountIdentifier) {
		super();
		this.success = success;
		this.message = message;
		this.errorCode = errorCode;
		this.accountIdentifier = accountIdentifier;
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

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	
	public static AppDirectResponse.AppDirectResponseBuilder builder(boolean success) {
		return new AppDirectResponseBuilder(success);
	}

	public static class AppDirectResponseBuilder {

		private boolean success;
		private String message;
		private ErrorCode errorCode;
		private String accountIdentifier;

		public AppDirectResponseBuilder(final boolean success) {
			this.success = success;
		}

		public AppDirectResponseBuilder message(String message) {
			this.message = message;
			return this;
		}

		public AppDirectResponseBuilder errorCode(ErrorCode errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		public AppDirectResponseBuilder accountIdentifier(String accountIdentifier) {
			this.accountIdentifier = accountIdentifier;
			return this;
		}

		public AppDirectResponse createResponse() {
			return new AppDirectResponse(success, message, errorCode, accountIdentifier);
		}
	}
}
