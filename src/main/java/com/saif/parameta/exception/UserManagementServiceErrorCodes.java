package com.saif.parameta.exception;

import org.springframework.http.HttpStatus;

public enum UserManagementServiceErrorCodes implements ServiceErrorMsg {

	USER_ALREADY_EXISTS("Document Number already exists", HttpStatus.BAD_REQUEST),

	DOCUMENT_NUMBER_NOT_FOUND("Document Number not found", HttpStatus.NOT_FOUND),

	DOCUMENT_TYPE_NOT_FOUND("Document TYPE not found", HttpStatus.NOT_FOUND),

	CARGO_NOT_FOUND("Job Position not found", HttpStatus.NOT_FOUND),

	SALARY_NOT_FOUND("Salary  not found", HttpStatus.NOT_FOUND),

	CRITERIA_NOT_FOUND("Date Criteria not found", HttpStatus.BAD_REQUEST),

	UNDER_AGE("Age is not legal", HttpStatus.BAD_REQUEST),

	EMPTY_DATABASE("No Data Found", HttpStatus.NOT_FOUND),

	EMPTY_NAME("EMPTY NAME FIELD", HttpStatus.BAD_REQUEST),

	GENERAL_EXCEPTION("General Exception", HttpStatus.BAD_REQUEST);

	private String message;
	private HttpStatus httpStatus;
	private String errorDetail;

	private UserManagementServiceErrorCodes(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	private UserManagementServiceErrorCodes(String message) {
		this.message = message;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
