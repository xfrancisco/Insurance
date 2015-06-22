package org.mfi.webservices;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.mfi.common.IBillService;
import org.mfi.common.IUtilService;
import org.mfi.exception.MfcException;
import org.mfi.out.billing.GlobalBillOut;
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

	@Inject
	private IUtilService utilService;

	@GET
	@Path("/bills")
	@ApiOperation(value = "Simulation de la facturation pour un contrat")
	public ResponseWrapper<GlobalBillOut> getCodeTable(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "Identifiant du client", name = PERSON_ID, required = true) @QueryParam(PERSON_ID) long personId,
			@ApiParam(value = "Identifiant du contrat", name = CONTRACT_ID, required = true) @QueryParam(CONTRACT_ID) int contractId)
			throws MfcException {
		ResponseWrapper<GlobalBillOut> responseWrapper = new ResponseWrapper<GlobalBillOut>();
		responseWrapper.setData(utilService.setNames(billService.getBills(userId, personId, contractId)));
		return responseWrapper;
	}
}
