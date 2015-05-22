package org.insurance.webservices;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.insurance.common.IPersonService;
import org.insurance.exception.InsuranceException;
import org.insurance.in.PersonIn;
import org.insurance.out.PersonOut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/person")
@Api(value = "person", description = "Personne physique ou morale")
public class PersonWebservice {

	@Inject
	private IPersonService personService;

	@POST
	@Path("/")
	@ApiOperation(value = "Création d'un individu")
	public ResponseWrapper<PersonOut> insertClient(@RequestBody PersonIn personIn) throws InsuranceException {
		ResponseWrapper<PersonOut> responseWrapper = new ResponseWrapper<PersonOut>();
		responseWrapper.setData(personService.insertPerson(personIn));
		return responseWrapper;
	}

	@GET
	@Path("/{personId}")
	@ApiOperation(value = "Données d'un individu")
	public ResponseWrapper<PersonOut> getCodeTable(@ApiParam(value = "personId", required = true) @PathParam("personId") Long personId)
			throws InsuranceException {
		ResponseWrapper<PersonOut> responseWrapper = new ResponseWrapper<PersonOut>();
		responseWrapper.setData(personService.getPerson(personId));
		return responseWrapper;
	}

}
