package org.mfi.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.mfi.common.IZipCodeService;
import org.mfi.exception.MfcException;
import org.mfi.in.ZipCodeIn;
import org.mfi.out.codes.ZipCodeOut;
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

	@POST
	@Path("/create")
	@ApiOperation(value = "Liste des individus correspondant aux catégories sélectionnées")
	public ResponseWrapper<ZipCodeOut> createZipCode(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid ZipCodeIn zipCodeIn) throws MfcException {
		ResponseWrapper<ZipCodeOut> responseWrapper = new ResponseWrapper<ZipCodeOut>();
		responseWrapper.setData(zipCodeService.createZipCode(userId, zipCodeIn));
		return responseWrapper;
	}

	@GET
	@Path("/zipCodesByCity")
	@ApiOperation(value = "Liste des codes postaux par commune")
	public ResponseWrapper<List<ZipCodeOut>> getZip(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Ville", name = CITY) @QueryParam(value = CITY) String city,
			@ApiParam(required = false, value = "Pays", name = COUNTRY_ID) @QueryParam(value = COUNTRY_ID) String country) throws MfcException {
		ResponseWrapper<List<ZipCodeOut>> responseWrapper = new ResponseWrapper<List<ZipCodeOut>>();
		responseWrapper.setData(zipCodeService.getZipCodesByCity(userId, city, country));
		return responseWrapper;
	}

	@GET
	@Path("/citiesByZipCode")
	@ApiOperation(value = "Liste des villes par code postal")
	public ResponseWrapper<List<ZipCodeOut>> getCity(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Ville", name = ZIPCODE) @QueryParam(value = ZIPCODE) String zipCode,
			@ApiParam(required = false, value = "Pays", name = COUNTRY_ID) @QueryParam(value = COUNTRY_ID) String country) throws MfcException {
		ResponseWrapper<List<ZipCodeOut>> responseWrapper = new ResponseWrapper<List<ZipCodeOut>>();
		responseWrapper.setData(zipCodeService.getZipCodesByZipCode(userId, zipCode, country));
		return responseWrapper;
	}

}
