package org.insurance.exception;

public class TimeOutException extends Exception {
	
	private static final long serialVersionUID = 8092069279536645503L;

	public TimeOutException(Throwable t) {
		super(t);
	}

	public TimeOutException(String msg, Throwable t) {
		super(msg, t);
	}

	public TimeOutException(String msg) {
		super(msg);
	}
}
