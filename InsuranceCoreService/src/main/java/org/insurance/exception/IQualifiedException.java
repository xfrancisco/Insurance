package org.insurance.exception;

public interface IQualifiedException {

	String getErrorCode();

	Object[] getMessageArgs();
}
