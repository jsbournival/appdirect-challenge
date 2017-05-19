package com.appdirect.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.appdirect.AppDirectResponse;

@ControllerAdvice
public class AppDirectExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AppDirectException.class)
	public @ResponseBody AppDirectResponse handleException(AppDirectException e) {
		return AppDirectResponse.builder(false).errorCode(e.getErrorCode()).message(e.getMessage()).createResponse();
	}
}
