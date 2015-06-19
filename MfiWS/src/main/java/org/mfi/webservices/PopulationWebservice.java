package org.mfi.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.mfi.common.IPopulationService;
import org.mfi.exception.MfcException;
import org.mfi.in.PopulationIn;
import org.mfi.out.person.PersonOut;
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

	@POST
	@Path("/populations")
	@ApiOperation(value = "Liste des individus correspondant aux catégories sélectionnées")
	public ResponseWrapper<List<PersonOut>> getPopulations(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid PopulationIn populationIn) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getPopulations(userId, populationIn, null));
		return responseWrapper;
	}

	@POST
	@Path("/populations/search")
	@ApiOperation(value = "Liste des individus correspondant aux catégories sélectionnées par nom")
	public ResponseWrapper<List<PersonOut>> getPopulationsByName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name,
			@Valid PopulationIn populationIn) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getPopulations(userId, populationIn, name));
		return responseWrapper;
	}

	@GET
	@Path("/clients")
	@ApiOperation(value = "Liste des clients")
	public ResponseWrapper<List<PersonOut>> getClients(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
					throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getClients(userId));
		return responseWrapper;
	}

	@GET
	@Path("/clients/search")
	@ApiOperation(value = "Liste des clients par nom")
	public ResponseWrapper<List<PersonOut>> getClientsByName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getClientsByName(userId, name));
		return responseWrapper;
	}

	@GET
	@Path("/brokers")
	@ApiOperation(value = "Liste des courtiers")
	public ResponseWrapper<List<PersonOut>> getBrokers(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
					throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getBrokers(userId));
		return responseWrapper;
	}

	@GET
	@Path("/brokers/search")
	@ApiOperation(value = "Liste des courtiers par nom")
	public ResponseWrapper<List<PersonOut>> getBrokersByName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getBrokersByName(userId, name));
		return responseWrapper;
	}

	@GET
	@Path("/insurers")
	@ApiOperation(value = "Liste des assureurs")
	public ResponseWrapper<List<PersonOut>> getInsurers(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
					throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getInsurers(userId));
		return responseWrapper;
	}

	@GET
	@Path("/insurers/search")
	@ApiOperation(value = "Liste des assureurs par nom")
	public ResponseWrapper<List<PersonOut>> getInsurersbyName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getInsurersbyName(userId, name));
		return responseWrapper;
	}

	@GET
	@Path("/reinsurers")
	@ApiOperation(value = "Liste des réassureurs")
	public ResponseWrapper<List<PersonOut>> getReinsurers(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
					throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getReinsurers(userId));
		return responseWrapper;
	}

	@GET
	@Path("/reinsurers/search")
	@ApiOperation(value = "Liste des réassureurs par nom")
	public ResponseWrapper<List<PersonOut>> getReinsurersByName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getReinsurersByName(userId, name));
		return responseWrapper;
	}

	@GET
	@Path("/experts")
	@ApiOperation(value = "Liste des experts")
	public ResponseWrapper<List<PersonOut>> getExperts(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
					throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getExperts(userId));
		return responseWrapper;
	}

	@GET
	@Path("/experts/search")
	@ApiOperation(value = "Liste des experts par nom")
	public ResponseWrapper<List<PersonOut>> getExpertsByName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getExpertsByName(userId, name));
		return responseWrapper;
	}

	@GET
	@Path("/lawyers")
	@ApiOperation(value = "Liste des avocats")
	public ResponseWrapper<List<PersonOut>> getLawyers(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
					throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getLawyers(userId));
		return responseWrapper;
	}

	@GET
	@Path("/lawyers/search")
	@ApiOperation(value = "Liste des avocats par nom")
	public ResponseWrapper<List<PersonOut>> getLawyersByName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getLawyersByName(userId, name));
		return responseWrapper;
	}

	@GET
	@Path("/beneficiaries")
	@ApiOperation(value = "Liste des benéficiaires")
	public ResponseWrapper<List<PersonOut>> getBeneficiaries(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
					throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getBeneficiaries(userId));
		return responseWrapper;
	}

	@GET
	@Path("/beneficiaries/search")
	@ApiOperation(value = "Liste des benéficiaires par nom")
	public ResponseWrapper<List<PersonOut>> getBeneficiariesByName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getBeneficiariesByName(userId, name));
		return responseWrapper;
	}

	@GET
	@Path("/thirdparties")
	@ApiOperation(value = "Liste des tiers")
	public ResponseWrapper<List<PersonOut>> getThirdParties(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
					throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getThirdParties(userId));
		return responseWrapper;
	}

	@GET
	@Path("/thirdparties/search")
	@ApiOperation(value = "Liste des tiers par nom")
	public ResponseWrapper<List<PersonOut>> getThirdPartiesByName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getThirdPartiesByName(userId, name));
		return responseWrapper;
	}

	@GET
	@Path("/underwritingagencies")
	@ApiOperation(value = "Liste des agences de souscription")
	public ResponseWrapper<List<PersonOut>> getAgencies(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
					throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getAgencies(userId));
		return responseWrapper;
	}

	@GET
	@Path("/underwritingagencies/search")
	@ApiOperation(value = "Liste des agences de souscription par nom")
	public ResponseWrapper<List<PersonOut>> getAgenciesByName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getAgenciesByName(userId, name));
		return responseWrapper;
	}

	@GET
	@Path("/all")
	@ApiOperation(value = "Toutes les populations")
	public ResponseWrapper<List<PersonOut>> getAll(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
					throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getAll(userId));
		return responseWrapper;
	}

	@GET
	@Path("/all/search")
	@ApiOperation(value = "Toutes les populations par nom")
	public ResponseWrapper<List<PersonOut>> getAllByName(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Nom ou raison sociale", name = NAME) @QueryParam(value = NAME) String name) throws MfcException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getAllByName(userId, name));
		return responseWrapper;
	}

}
