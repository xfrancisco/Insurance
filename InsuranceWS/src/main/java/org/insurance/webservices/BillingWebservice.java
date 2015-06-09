package org.insurance.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.insurance.common.IBillService;
import org.insurance.exception.InsuranceException;
import org.insurance.out.BillOut;
import org.springframework.stereotype.Controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/billing")
@Api(value = "billing", description = "Facturation")
public class BillingWebservice extends AbstractWebservice {
	@Inject
	private IBillService billService;

	@GET
	@Path("/bills")
	@ApiOperation(value = "Simulation de la facturation pour un contrat")
	public ResponseWrapper<List<BillOut>> getCodeTable(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "Identifiant du client", name = PERSON_ID, required = true) @QueryParam(PERSON_ID) long personId,
			@ApiParam(value = "Identifiant du contrat", name = CONTRACT_ID, required = true) @QueryParam(CONTRACT_ID) int contractId)
			throws InsuranceException {
		ResponseWrapper<List<BillOut>> responseWrapper = new ResponseWrapper<List<BillOut>>();
		responseWrapper.setData(billService.getBills(userId, personId, contractId));
		return responseWrapper;
	}

}
