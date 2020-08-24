package com.saif.parameta.model;

public class ServiceExceptionWrapper {

	String errorCode;

	String errorMessage;

	public ServiceExceptionWrapper(String message, String code) {
		this.errorCode = code;
		this.errorMessage = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return String.format("ServiceExceptionWrapper [errorCode=%s, errorMessage=%s]", errorCode, errorMessage);
	}

}
