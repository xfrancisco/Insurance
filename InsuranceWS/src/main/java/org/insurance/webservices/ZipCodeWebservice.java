package org.insurance.webservices;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.insurance.common.IZipCodeService;
import org.insurance.exception.InsuranceException;
import org.insurance.out.ZipCodeOut;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/zipcode")
@Api(value = "zipcode", description = "Codes postaux")
@Validated
public class ZipCodeWebservice extends AbstractWebservice {

	@Inject
	private IZipCodeService zipCodeService;

	/*@POST
	@Path("/create")
	@ApiOperation(value = "Liste des individus correspondant aux catégories sélectionnées")
	public ResponseWrapper<List<PersonOut>> getPopulations(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid PopulationIn populationIn) throws InsuranceException {
		ResponseWrapper<List<PersonOut>> responseWrapper = new ResponseWrapper<List<PersonOut>>();
		responseWrapper.setData(populationService.getPopulations(userId, populationIn));
		return responseWrapper;
	}*/

	@GET
	@Path("/zipcode")
	@ApiOperation(value = "Liste des codes postaux par commune")
	public ResponseWrapper<ZipCodeOut> getZip(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Ville", name = CITY) @QueryParam(value = CITY) String city,
			@ApiParam(required = false, value = "Pays", name = COUNTRY_ID) @QueryParam(value = COUNTRY_ID) String country) throws InsuranceException {
		ResponseWrapper<ZipCodeOut> responseWrapper = new ResponseWrapper<ZipCodeOut>();
		responseWrapper.setData(zipCodeService.getZipCodesByCity(userId, city, country));
		return responseWrapper;
	}

	@GET
	@Path("/city")
	@ApiOperation(value = "Liste des villes par code postal")
	public ResponseWrapper<ZipCodeOut> getCity(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Ville", name = ZIPCODE) @QueryParam(value = ZIPCODE) String zipCode,
			@ApiParam(required = false, value = "Pays", name = COUNTRY_ID) @QueryParam(value = COUNTRY_ID) String country) throws InsuranceException {
		ResponseWrapper<ZipCodeOut> responseWrapper = new ResponseWrapper<ZipCodeOut>();
		responseWrapper.setData(zipCodeService.getZipCodesByZipCode(userId, zipCode, country));
		return responseWrapper;
	}

}
