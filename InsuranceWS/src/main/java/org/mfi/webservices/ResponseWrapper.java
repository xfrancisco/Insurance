package org.mfi.webservices;

public class ResponseWrapper<T> {

	public enum Severity {
		WARN,
		ERROR,
		FATAL,
		SUCCESS
	}

	private String returnCode;

	private String message;

	private String additionalMessage;

	private Severity severity;

	private T data;

	public ResponseWrapper() {
		this.returnCode = "SUCCESS";
		this.message = "Process successfully completed";
		this.severity = Severity.SUCCESS;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getAdditionalMessage() {
		return additionalMessage;
	}

	public void setAdditionalMessage(String additionalMessage) {
		this.additionalMessage = additionalMessage;
	}

}
