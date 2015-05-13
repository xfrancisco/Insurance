package org.insurance.exception;

import java.util.Arrays;

public abstract class GenericTechnicalException extends RuntimeException implements IQualifiedException {

	private static final long serialVersionUID = -3511342366398604449L;

	private final Enum<?> errorCode;

	private final Object[] messageArgs;
	
	public static final Object[] EMPTY_ARRAY = {};

	public <E extends Enum<?>> GenericTechnicalException(E errorCode) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.messageArgs = EMPTY_ARRAY;
	}

	public <E extends Enum<?>> GenericTechnicalException(E errorCode, Object... messageArgs) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
	}

	public <E extends Enum<?>> GenericTechnicalException(E errorCode, Throwable cause) {
		super(errorCode.name(), cause);
		this.errorCode = errorCode;
		this.messageArgs = EMPTY_ARRAY;
	}

	public <E extends Enum<?>> GenericTechnicalException(E errorCode, Throwable cause, Object... messageArgs) {
		super(errorCode.name(), cause);
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
	}
	
	public String getErrorCode() {
		return errorCode.name();
	}

	public Object[] getMessageArgs() {
		return Arrays.copyOf(this.messageArgs, this.messageArgs.length);
	}

	public Enum<?> getEnumErrorCode() {
		return errorCode;
	}
}
