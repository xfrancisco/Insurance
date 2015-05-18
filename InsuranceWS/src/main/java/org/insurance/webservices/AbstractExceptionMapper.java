package org.insurance.webservices;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.insurance.exception.InsuranceException;
import org.insurance.exception.TechnicalException;
import org.insurance.utils.PropertiesUtils;
import org.insurance.webservices.ResponseWrapper.Severity;
import org.springframework.stereotype.Component;

@Component
@Provider
public class AbstractExceptionMapper implements ExceptionMapper<Exception>{

	@Inject
	private PropertiesUtils propertiesUtils;
	
	@Override
	public Response toResponse(Exception exception) {

		ResponseBuilder<String> builder = new ResponseBuilder<>();
		ResponseWrapper<String> wrapper = null;
		if (exception instanceof InsuranceException){
			InsuranceException tmp = (InsuranceException) exception;
			builder.addReturnCode(tmp.getErrorCode())
			.addMessage(propertiesUtils.getMessage(tmp.getErrorCode(), tmp.getMessageArgs(), exception.getMessage()));
		}
		else{
			builder.addReturnCode(TechnicalException.ErrorCode.ERR_TECH_GEN_DEFAULT.name())
					.addMessage(propertiesUtils.getMessage(TechnicalException.ErrorCode.ERR_TECH_GEN_DEFAULT.name(), new Object[] {}, ""))
					.addSeverity(Severity.ERROR);
		}
		/*if (isStackTracePrintingEnabled) {
		builder.addData(ExceptionUtils.getStackTrace(exception));
		}*/
		wrapper = builder.getResponseWrapper();
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(wrapper).build();
	}
		
		
		
}
	
	
