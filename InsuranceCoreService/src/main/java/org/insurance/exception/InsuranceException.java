package org.insurance.exception;

import java.util.Arrays;

public class InsuranceException extends Exception {

	private static final long serialVersionUID = -2552269031889664398L;

	private String errorCode;

	private Object[] messageArgs;

	public InsuranceException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public InsuranceException(String errorCode, Object... messageArgs) {
		super();
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
	}

	public InsuranceException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public InsuranceException(String errorCode, String errorMessage, Object... messageArgs) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
	}

	public InsuranceException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public InsuranceException(String errorCode, Throwable cause, Object... messageArgs) {
		super(cause);
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
	}

	public InsuranceException(String errorCode, String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorCode = errorCode;
	}

	public InsuranceException(Throwable cause, String errorCode, Object... messageArgs) {
		super(cause);
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getMessageArgs() {
		Object[] localMessageArgs = null;
		if (this.messageArgs != null) {
			localMessageArgs = Arrays.copyOf(this.messageArgs, this.messageArgs.length);
		}
		return localMessageArgs;
	}
}
