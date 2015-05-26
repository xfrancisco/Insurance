package org.insurance.webservices;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.insurance.common.IPersonService;
import org.insurance.exception.InsuranceException;
import org.insurance.in.InsertPersonIn;
import org.insurance.in.UpdatePersonIn;
import org.insurance.out.PersonOut;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/person")
@Api(value = "person", description = "Personne physique ou morale")
@Validated
public class PersonWebservice extends AbstractWebservice {

	@Inject
	private IPersonService personService;

	@POST
	@Path("/create")
	@ApiOperation(value = "Création d'un individu")
	public ResponseWrapper<PersonOut> insertClient(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid InsertPersonIn insertPersonIn) throws InsuranceException {
		ResponseWrapper<PersonOut> responseWrapper = new ResponseWrapper<PersonOut>();
		responseWrapper.setData(personService.insertPerson(userId, insertPersonIn));
		return responseWrapper;
	}

	@PUT
	@Path("/update")
	@ApiOperation(value = "Mise à jour d'un individu")
	public ResponseWrapper<PersonOut> updateClient(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid UpdatePersonIn updatePersonIn) throws InsuranceException {
		ResponseWrapper<PersonOut> responseWrapper = new ResponseWrapper<PersonOut>();
		responseWrapper.setData(personService.updatePerson(userId, updatePersonIn));
		return responseWrapper;
	}

	@GET
	@Path("/{personId}")
	@ApiOperation(value = "Données d'un individu")
	public ResponseWrapper<PersonOut> getCodeTable(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "personId", required = true) @PathParam("personId") Long personId) throws InsuranceException {
		ResponseWrapper<PersonOut> responseWrapper = new ResponseWrapper<PersonOut>();
		responseWrapper.setData(personService.getPerson(userId, personId));
		return responseWrapper;
	}

}
