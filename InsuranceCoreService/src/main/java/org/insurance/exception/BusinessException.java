package org.insurance.exception;

import java.util.Arrays;


public abstract class BusinessException extends Exception implements IQualifiedException {

	private static final long serialVersionUID = 3962094255501364678L;

	private final Enum<?> errorCode;

	private final Object[] messageArgs;

	private final String additionalMessage;
	
	public static final Object[] EMPTY_ARRAY = {};

	public <E extends Enum<?>> BusinessException(E errorCode) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.messageArgs = EMPTY_ARRAY;
		this.additionalMessage = null;
	}

	public <E extends Enum<?>> BusinessException(E errorCode, Object... messageArgs) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
		this.additionalMessage = null;
	}

	public <E extends Enum<?>> BusinessException(E errorCode, Throwable cause) {
		super(errorCode.name(), cause);
		this.errorCode = errorCode;
		this.messageArgs = EMPTY_ARRAY;
		this.additionalMessage = null;
	}

	public <E extends Enum<?>> BusinessException(E errorCode, Throwable cause, Object... messageArgs) {
		super(errorCode.name(), cause);
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
		this.additionalMessage = null;
	}

	public <E extends Enum<?>> BusinessException(E errorCode, String additionalMessage, Throwable cause, Object... messageArgs) {
		super(errorCode.name(), cause);
		this.errorCode = errorCode;
		this.messageArgs = messageArgs;
		this.additionalMessage = additionalMessage;
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

	public String getAdditionalMessage() {
		return additionalMessage;
	}
}
