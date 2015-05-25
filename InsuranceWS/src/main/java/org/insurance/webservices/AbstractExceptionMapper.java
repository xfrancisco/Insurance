package org.insurance.webservices;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Path.Node;
import javax.validation.metadata.ConstraintDescriptor;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.insurance.exception.InsuranceException;
import org.insurance.exception.TechnicalException;
import org.insurance.utils.PropertiesUtils;
import org.insurance.validation.constraints.Length;
import org.insurance.webservices.ResponseWrapper.Severity;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
@Provider
public class AbstractExceptionMapper implements ExceptionMapper<Exception> {

	@Inject
	private PropertiesUtils propertiesUtils;

	private static final String GENERIC_FIELD_SUFFIX = "_FIELD";

	@Override
	public Response toResponse(Exception exception) {
		//TODO XFR : print de la stack
		ResponseBuilder<String> builder = new ResponseBuilder<>();
		ResponseWrapper<String> wrapper = null;
		if (exception instanceof InsuranceException) {
			// Erreurs business
			InsuranceException tmp = (InsuranceException) exception;
			builder.addReturnCode(tmp.getErrorCode())
					.addMessage(propertiesUtils.getMessage(tmp.getErrorCode(), tmp.getMessageArgs(), exception.getMessage()))
					.addSeverity(Severity.ERROR);
		} else if (exception instanceof ConstraintViolationException) {
			// Erreurs validation JSR-303
			ConstraintViolationException myException = (ConstraintViolationException) exception;
			List<ConstraintViolation<?>> constraintViolations = Lists.newArrayList(myException.getConstraintViolations());
			List<String> errorMessages = new ArrayList<>();
			if (!constraintViolations.isEmpty()) {
				ConstraintViolation<?> constraintViolation = constraintViolations.get(0);

				// Gestion spécifique de l'annotation Length
				ConstraintDescriptor<?> descriptor = constraintViolation.getConstraintDescriptor();
				Annotation annotation = descriptor.getAnnotation();
				int min = 0;
				int max = 0;
				boolean isLength = false;
				if (annotation instanceof Length) {
					Length length = (Length) annotation;
					min = length.min().length;
					max = length.max().length;
					isLength = true;
				}

				// Traitement commun à toutes les annotations (Mandatory, Length...)
				String errorCode = constraintViolation.getMessage();
				if (errorCode.endsWith(GENERIC_FIELD_SUFFIX)) {
					Path path = constraintViolation.getPropertyPath();
					String field = null;
					for (Node node : path) {
						field = node.getName();
					}
					List<String> errorMessageArgs = new ArrayList<>();
					errorMessageArgs.add(field);
					errorMessageArgs.add(String.valueOf(constraintViolation.getInvalidValue()));
					if (isLength) {
						// Gestion spécifique de l'annotation Length
						errorMessageArgs.add(String.valueOf(max));
						errorMessageArgs.add(String.valueOf(min));
					}
					errorMessages.add(propertiesUtils.getMessage(errorCode, errorMessageArgs.toArray(), exception.getMessage()));
					builder.addReturnCode(exception.getMessage()).addMessage(errorMessages.get(0)).addSeverity(Severity.ERROR);
				}
			}
		} else {
			// toutes les autres erreurs
			builder.addReturnCode(TechnicalException.ErrorCode.ERR_TECH_GEN_DEFAULT.name())
					.addMessage(propertiesUtils.getMessage(TechnicalException.ErrorCode.ERR_TECH_GEN_DEFAULT.name(), new Object[] {}, ""))
					.addSeverity(Severity.ERROR);
		}
		//TODO XFR : Ajout de la stack dans le flux de sortie

		/*if (isStackTracePrintingEnabled) {
		builder.addData(ExceptionUtils.getStackTrace(exception));
		}*/
		exception.printStackTrace();
		wrapper = builder.getResponseWrapper();
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(wrapper).build();
	}
}
