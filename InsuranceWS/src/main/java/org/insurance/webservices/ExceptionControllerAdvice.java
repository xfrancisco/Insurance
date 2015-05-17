package org.insurance.webservices;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.insurance.exception.BusinessException;
import org.insurance.exception.GenericTechnicalException;
import org.insurance.utils.PropertiesUtils;
import org.insurance.webservices.ResponseWrapper.Severity;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.common.collect.Lists;

@ControllerAdvice
@EnableWebMvc
public class ExceptionControllerAdvice {

	@Inject
	@Named("validationMessageSource")
	private MessageSource validationMessageSource;

	@Inject
	private PropertiesUtils propertiesUtils;

	private boolean isStackTracePrintingEnabled = false;

	private static final String GENERIC_FIELD_SUFFIX = "_FIELD";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.NOT_EXTENDED)
	@ResponseBody
	public ResponseWrapper<List<String>> handleValidationException(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		List<String> errorMessages = new ArrayList<>();
		for (FieldError fieldError : fieldErrors) {
			String errorCode = fieldError.getDefaultMessage();

			List<String> errorMessageArgs = new ArrayList<>();
			if (errorCode.endsWith(GENERIC_FIELD_SUFFIX)) {
				errorMessageArgs.add(fieldError.getField());
			}
			errorMessageArgs.add(String.valueOf(fieldError.getRejectedValue()));

			errorMessages.add(validationMessageSource.getMessage(errorCode, errorMessageArgs.toArray(), "??????", Locale.getDefault()));
		}

		ResponseBuilder<List<String>> builder = new ResponseBuilder<>();
		builder.addReturnCode(fieldErrors.get(0).getDefaultMessage()).addMessage(errorMessages.get(0)).addSeverity(Severity.ERROR);
		//builder.addData(errorMessages);
		return builder.getResponseWrapper();
	}

	@ExceptionHandler(MethodConstraintViolationException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseWrapper<List<String>> handleValidationException(MethodConstraintViolationException exception) {
		List<MethodConstraintViolation<?>> methodConstraintViolations = Lists.newArrayList(exception.getConstraintViolations());

		List<String> errorMessages = new ArrayList<>();
		for (MethodConstraintViolation<?> methodConstraintViolation : methodConstraintViolations) {
			String errorCode = methodConstraintViolation.getMessage();

			List<String> errorMessageArgs = new ArrayList<>();
			if (errorCode.endsWith(GENERIC_FIELD_SUFFIX)) {
				Method method = methodConstraintViolation.getMethod();

				Annotation[][] parameterAnnotations = method.getParameterAnnotations();

				errorMessageArgs.add(findErrorField(parameterAnnotations, methodConstraintViolation.getParameterIndex()));
			}
			errorMessageArgs.add(String.valueOf(methodConstraintViolation.getInvalidValue()));

			errorMessages.add(validationMessageSource.getMessage(errorCode, errorMessageArgs.toArray(), "??????", Locale.getDefault()));
		}

		ResponseBuilder<List<String>> builder = new ResponseBuilder<>();
		builder.addReturnCode(methodConstraintViolations.get(0).getMessage()).addMessage(errorMessages.get(0)).addSeverity(Severity.ERROR);
		//builder.addData(errorMessages);
		return builder.getResponseWrapper();
	}

	private String findErrorField(Annotation[][] parameterAnnotations, int parameterIndex) {
		String errorField = null;

		Annotation[] annotations = parameterAnnotations[parameterIndex];

		Iterator<Annotation> annotationsIterator = Lists.newArrayList(annotations).iterator();
		while (annotationsIterator.hasNext() && errorField == null) {
			Annotation annotation = annotationsIterator.next();
			if (annotation instanceof RequestParam) {
				RequestParam requestParamAnnotation = (RequestParam) annotation;

				errorField = requestParamAnnotation.value();
			}
		}

		return errorField;
	}

	@ExceptionHandler(GenericTechnicalException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseWrapper<String> handleTechnicalException(GenericTechnicalException exception) {
		ResponseBuilder<String> builder = new ResponseBuilder<>();
		builder.addReturnCode(exception.getErrorCode())
				.addMessage(propertiesUtils.getMessage(exception.getErrorCode(), exception.getMessageArgs(), exception.getMessage()))
				.addSeverity(Severity.ERROR);
		if (isStackTracePrintingEnabled) {
			builder.addData(ExceptionUtils.getStackTrace(exception));
		}
		return builder.getResponseWrapper();
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.NOT_EXTENDED)
	@ResponseBody
	public ResponseWrapper<BusinessException> handleBusinessException(BusinessException exception) {
		ResponseBuilder<BusinessException> builder = new ResponseBuilder<>();
		builder.addReturnCode(exception.getErrorCode())
				.addMessage(propertiesUtils.getMessage(exception.getErrorCode(), exception.getMessageArgs(), exception.getMessage()))
				.addAdditionalMessage(exception.getAdditionalMessage()).addSeverity(Severity.ERROR);
		return builder.getResponseWrapper();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseWrapper<String> handleGenericException(Exception exception) {
		ResponseBuilder<String> builder = new ResponseBuilder<>();
		builder.addReturnCode(TechnicalException.ErrorCode.ERR_TECH_GEN_DEFAULT.name())
				.addMessage(propertiesUtils.getMessage(TechnicalException.ErrorCode.ERR_TECH_GEN_DEFAULT.name(), new Object[] {}, ""))
				.addSeverity(Severity.ERROR);
		if (isStackTracePrintingEnabled) {
			builder.addData(ExceptionUtils.getStackTrace(exception));
		}
		return builder.getResponseWrapper();
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseWrapper<String> handleGenericException(NullPointerException exception) {
		ResponseBuilder<String> builder = new ResponseBuilder<>();
		builder.addReturnCode(TechnicalException.ErrorCode.ERR_TECH_GEN_DEFAULT.name())
				.addMessage(propertiesUtils.getMessage(TechnicalException.ErrorCode.ERR_TECH_GEN_DEFAULT.name(), new Object[] {}, ""))
				.addSeverity(Severity.ERROR);
		if (isStackTracePrintingEnabled) {
			builder.addData(ExceptionUtils.getStackTrace(exception));
		}
		return builder.getResponseWrapper();
	}

}
