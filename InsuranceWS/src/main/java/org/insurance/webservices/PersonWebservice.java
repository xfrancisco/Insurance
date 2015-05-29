package org.insurance.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.insurance.common.IPersonService;
import org.insurance.exception.InsuranceException;
import org.insurance.in.InsertPersonIn;
import org.insurance.in.UpdatePersonIn;
import org.insurance.out.AddressHistoryOut;
import org.insurance.out.EmailHistoryOut;
import org.insurance.out.PersonOut;
import org.insurance.out.PhoneHistoryOut;
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
	public ResponseWrapper<PersonOut> insertPerson(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid InsertPersonIn insertPersonIn) throws InsuranceException {
		ResponseWrapper<PersonOut> responseWrapper = new ResponseWrapper<PersonOut>();
		responseWrapper.setData(personService.insertPerson(userId, insertPersonIn));
		return responseWrapper;
	}

	@PUT
	@Path("/update")
	@ApiOperation(value = "Mise à jour d'un individu")
	public ResponseWrapper<PersonOut> updatePerson(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid UpdatePersonIn updatePersonIn) throws InsuranceException {
		ResponseWrapper<PersonOut> responseWrapper = new ResponseWrapper<PersonOut>();
		responseWrapper.setData(personService.updatePerson(userId, updatePersonIn));
		return responseWrapper;
	}

	@GET
	@Path("/detail")
	@ApiOperation(value = "Données d'un individu")
	public ResponseWrapper<PersonOut> getPerson(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "personId", required = true) @QueryParam(PERSON_ID) Long personId) throws InsuranceException {
		ResponseWrapper<PersonOut> responseWrapper = new ResponseWrapper<PersonOut>();
		responseWrapper.setData(personService.getPerson(userId, personId));
		return responseWrapper;
	}

	@GET
	@Path("/addressHistory")
	@ApiOperation(value = "Historique des adresses")
	public ResponseWrapper<List<AddressHistoryOut>> getAddressHistory(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "personId", required = true) @QueryParam(PERSON_ID) Long personId) throws InsuranceException {
		ResponseWrapper<List<AddressHistoryOut>> responseWrapper = new ResponseWrapper<List<AddressHistoryOut>>();
		responseWrapper.setData(personService.getAddressHistory(userId, personId));
		return responseWrapper;
	}

	@GET
	@Path("/phoneHistory")
	@ApiOperation(value = "Historique des numéros de téléphone")
	public ResponseWrapper<List<PhoneHistoryOut>> getPhoneHistory(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "personId", required = true) @QueryParam(PERSON_ID) Long personId) throws InsuranceException {
		ResponseWrapper<List<PhoneHistoryOut>> responseWrapper = new ResponseWrapper<List<PhoneHistoryOut>>();
		responseWrapper.setData(personService.getPhoneHistory(userId, personId));
		return responseWrapper;
	}

	@GET
	@Path("/emailHistory")
	@ApiOperation(value = "Historique des adresses")
	public ResponseWrapper<List<EmailHistoryOut>> getEmailHistory(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "personId", required = true) @QueryParam(PERSON_ID) Long personId) throws InsuranceException {
		ResponseWrapper<List<EmailHistoryOut>> responseWrapper = new ResponseWrapper<List<EmailHistoryOut>>();
		responseWrapper.setData(personService.getEmailHistory(userId, personId));
		return responseWrapper;
	}

}
