package org.insurance.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.insurance.common.IPopulationService;
import org.insurance.exception.InsuranceException;
import org.insurance.out.PersonOut;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/population")
@Api(value = "population", description = "Population")
@Validated
public class PopulationWebservice extends AbstractWebservice {

	@Inject
	private IPopulationService populationService;

	/*@GET
	@Path("/populations")
	@ApiOperation(value = "Liste des individus correspondant aux catégories sélectionnées")
	public ResponseWrapper<List<PersonOut>> getPopulations(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid PopulationIn populationIn) throws InsuranceException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getPopulations(userId, populationIn));
		return responseWrapper;
	}*/

	@GET
	@Path("/clients")
	@ApiOperation(value = "Liste des clients")
	public ResponseWrapper<List<PersonOut>> getClients(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getClients(userId));
		return responseWrapper;
	}

	@GET
	@Path("/brokers")
	@ApiOperation(value = "Liste des courtiers")
	public ResponseWrapper<List<PersonOut>> getBrokers(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getBrokers(userId));
		return responseWrapper;
	}

	@GET
	@Path("/insurers")
	@ApiOperation(value = "Liste des assureurs")
	public ResponseWrapper<List<PersonOut>> getInsurers(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getInsurers(userId));
		return responseWrapper;
	}

	@GET
	@Path("/reinsurers")
	@ApiOperation(value = "Liste des assureurs")
	public ResponseWrapper<List<PersonOut>> getReinsurers(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getReinsurers(userId));
		return responseWrapper;
	}

	@GET
	@Path("/experts")
	@ApiOperation(value = "Liste des experts")
	public ResponseWrapper<List<PersonOut>> getExperts(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getExperts(userId));
		return responseWrapper;
	}

	@GET
	@Path("/lawyers")
	@ApiOperation(value = "Liste des avocats")
	public ResponseWrapper<List<PersonOut>> getLawyers(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getLawyers(userId));
		return responseWrapper;
	}

	@GET
	@Path("/beneficiaries")
	@ApiOperation(value = "Liste des benéficiaires")
	public ResponseWrapper<List<PersonOut>> getBeneficiaries(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getBeneficiaries(userId));
		return responseWrapper;
	}

	@GET
	@Path("/thirdparties")
	@ApiOperation(value = "Liste des tiers")
	public ResponseWrapper<List<PersonOut>> getThirdParties(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getThirdParties(userId));
		return responseWrapper;
	}

}
