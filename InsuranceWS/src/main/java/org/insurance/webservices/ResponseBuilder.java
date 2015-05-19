package org.insurance.webservices;

import org.insurance.webservices.ResponseWrapper.Severity;

public class ResponseBuilder<T> {

	private ResponseWrapper<T> responseWrapper;

	public ResponseBuilder() {
		responseWrapper = new ResponseWrapper<T>();
	}

	public ResponseWrapper<T> getResponseWrapper() {
		return responseWrapper;
	}

	public ResponseBuilder<T> addReturnCode(String returnCode) {
		responseWrapper.setReturnCode(returnCode);
		return this;
	}

	public ResponseBuilder<T> addMessage(String message) {
		responseWrapper.setMessage(message);
		return this;
	}

	public ResponseBuilder<T> addAdditionalMessage(String additionalMessage) {
		responseWrapper.setAdditionalMessage(additionalMessage);
		return this;
	}

	public ResponseBuilder<T> addSeverity(Severity severity) {
		responseWrapper.setSeverity(severity);
		return this;
	}

	public ResponseBuilder<T> addData(T data) {
		responseWrapper.setData(data);
		return this;
	}

}
