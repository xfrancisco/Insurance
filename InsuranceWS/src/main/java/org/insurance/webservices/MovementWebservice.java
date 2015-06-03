package org.insurance.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.insurance.common.IMovementService;
import org.insurance.exception.InsuranceException;
import org.insurance.out.movements.MovementDetailsOut;
import org.insurance.out.movements.MovementOut;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/movement")
@Api(value = "movement", description = "Movements")
@Validated
public class MovementWebservice extends AbstractWebservice {

	@Inject
	private IMovementService movementService;

	@GET
	@Path("/detail")
	@ApiOperation(value = "Détail d'un mouvement")
	public ResponseWrapper<MovementDetailsOut> getMovementDetails(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "Identifiant du mouvement", name = MOVEMENT_ID, required = true) @QueryParam(value = MOVEMENT_ID) Long movementId)
			throws InsuranceException {
		ResponseWrapper<MovementDetailsOut> responseWrapper = new ResponseWrapper<MovementDetailsOut>();
		responseWrapper.setData(movementService.getMovement(userId, movementId));
		return responseWrapper;
	}

	@GET
	@Path("/list")
	@ApiOperation(value = "Liste des mouvements")
	public ResponseWrapper<List<MovementOut>> getMovements(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "Numéro de client", name = PERSON_ID, required = true) @QueryParam(value = PERSON_ID) Long personId,
			@ApiParam(value = "Numéro de contrat", name = CONTRACT_ID, required = false) @QueryParam(value = CONTRACT_ID) Integer contractId,
			@ApiParam(value = "Numéro de proposition", name = QUOTE_ID, required = false) @QueryParam(value = QUOTE_ID) Integer quoteId)
			throws InsuranceException {
		ResponseWrapper<List<MovementOut>> responseWrapper = new ResponseWrapper<List<MovementOut>>();
		responseWrapper.setData(movementService.getMovements(userId, personId, contractId, quoteId));
		return responseWrapper;
	}

}
