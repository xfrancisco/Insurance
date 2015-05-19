package org.insurance.webservices;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.insurance.InsuranceEnterpriseModel.in.ClientIn;
import org.insurance.InsuranceEnterpriseModel.out.ClientOut;
import org.insurance.common.IPersonService;
import org.insurance.exception.InsuranceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@Path("/person")
@Api(value = "client", description = "Client")
public class PersonWebservice {

	@Inject
	private IPersonService personService;

	@POST
	@Path("/")
	@ApiOperation(value = "Création d'un nouveau client")
	public ResponseWrapper<ClientOut> insertClient(@RequestBody ClientIn clientIn) throws InsuranceException {
		ResponseWrapper<ClientOut> responseWrapper = new ResponseWrapper<ClientOut>();
		responseWrapper.setData(personService.insertClient(clientIn));
		return responseWrapper;
	}

}
