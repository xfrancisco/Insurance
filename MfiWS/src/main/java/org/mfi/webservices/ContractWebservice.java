package org.mfi.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.mfi.common.IContractService;
import org.mfi.exception.MfcException;
import org.mfi.in.ContractIn;
import org.mfi.in.UpdateContractIn;
import org.mfi.out.ContractListOut;
import org.mfi.out.ContractOut;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/contract")
@Api(value = "contract", description = "Contrats")
@Validated
public class ContractWebservice extends AbstractWebservice {
	@Inject
	private IContractService contractService;

	@POST
	@Path("/create")
	@ApiOperation(value = "Création d'un contrat")
	public ResponseWrapper<ContractOut> insertContract(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid ContractIn newContractIn) throws MfcException {
		ResponseWrapper<ContractOut> responseWrapper = new ResponseWrapper<ContractOut>();
		responseWrapper.setData(contractService.insertContract(userId, newContractIn));
		return responseWrapper;
	}

	@PUT
	@Path("/update")
	@ApiOperation(value = "Mise à jour d'un contrat")
	public ResponseWrapper<ContractOut> updateContract(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid UpdateContractIn updateContractIn) throws MfcException {
		ResponseWrapper<ContractOut> responseWrapper = new ResponseWrapper<ContractOut>();
		responseWrapper.setData(contractService.updateContract(userId, updateContractIn));
		return responseWrapper;
	}

	@GET
	@Path("/detail")
	@ApiOperation(value = "Détail d'un contrat")
	public ResponseWrapper<ContractOut> getContract(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Client", name = PERSON_ID) @QueryParam(value = PERSON_ID) Long personId,
			@ApiParam(required = true, value = "Contrat", name = CONTRACT_ID) @QueryParam(value = CONTRACT_ID) Integer contractId)
			throws MfcException {
		ResponseWrapper<ContractOut> responseWrapper = new ResponseWrapper<ContractOut>();
		responseWrapper.setData(contractService.getContract(userId, personId, contractId));
		return responseWrapper;
	}

	@GET
	@Path("/list")
	@ApiOperation(value = "Liste des contrats d'un client ")
	public ResponseWrapper<List<ContractListOut>> getContracts(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Client", name = PERSON_ID) @QueryParam(value = PERSON_ID) Long personId) throws MfcException {
		ResponseWrapper<List<ContractListOut>> responseWrapper = new ResponseWrapper<List<ContractListOut>>();
		responseWrapper.setData(contractService.getContracts(userId, personId));
		return responseWrapper;
	}

}
