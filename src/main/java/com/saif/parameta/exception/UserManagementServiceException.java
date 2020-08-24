package com.saif.parameta.exception;

public class UserManagementServiceException extends Exception {

	private static final long serialVersionUID = 7718828512143293558L;

	private final UserManagementServiceErrorCodes code;

	public UserManagementServiceException(UserManagementServiceErrorCodes code) {
		super(code.getErrorDetail());
		this.code = code;
	}

	public UserManagementServiceErrorCodes getCode() {
		return code;
	}

}
