package org.mfi.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.mfi.common.IProtocolService;
import org.mfi.exception.MfcException;
import org.mfi.in.ProtocolIn;
import org.mfi.in.UpdateProtocolIn;
import org.mfi.out.ProtocolOut;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/protocol")
@Api(value = "protocol", description = "Protocoles")
@Validated
public class ProtocolWebservice extends AbstractWebservice {
	@Inject
	private IProtocolService protocolService;

	@POST
	@Path("/create")
	@ApiOperation(value = "Création d'un protocole")
	public ResponseWrapper<ProtocolOut> insertProtocol(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid ProtocolIn newProtocolIn) throws MfcException {
		ResponseWrapper<ProtocolOut> responseWrapper = new ResponseWrapper<ProtocolOut>();
		responseWrapper.setData(protocolService.insertProtocol(userId, newProtocolIn));
		return responseWrapper;
	}

	@PUT
	@Path("/update")
	@ApiOperation(value = "Mise à jour d'un protocole")
	public ResponseWrapper<ProtocolOut> updateProtocol(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid UpdateProtocolIn updateProtocolIn) throws MfcException {
		ResponseWrapper<ProtocolOut> responseWrapper = new ResponseWrapper<ProtocolOut>();
		responseWrapper.setData(protocolService.updateProtocol(userId, updateProtocolIn));
		return responseWrapper;
	}

	@GET
	@Path("/detail")
	@ApiOperation(value = "Détail d'un protocole")
	public ResponseWrapper<ProtocolOut> getProtocol(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = false, value = "Identifiant du protocole", name = PROTOCOL_ID) @QueryParam(value = PROTOCOL_ID) Long protocolId,
			@ApiParam(required = false, value = "Intitulé du protocole", name = PROTOCOL_NAME) @QueryParam(value = PROTOCOL_NAME) String protocolName)
			throws MfcException {
		ResponseWrapper<ProtocolOut> responseWrapper = new ResponseWrapper<ProtocolOut>();
		responseWrapper.setData(protocolService.getProtocol(userId, protocolId, protocolName));
		return responseWrapper;
	}

	@GET
	@Path("/list")
	@ApiOperation(value = "Liste des protocoles ")
	public ResponseWrapper<List<ProtocolOut>> getContracts(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws MfcException {
		ResponseWrapper<List<ProtocolOut>> responseWrapper = new ResponseWrapper<List<ProtocolOut>>();
		responseWrapper.setData(protocolService.getProtocols(userId));
		return responseWrapper;
	}

}
