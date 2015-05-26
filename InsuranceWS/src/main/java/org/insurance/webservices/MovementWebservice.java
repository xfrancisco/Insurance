package org.insurance.webservices;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.insurance.common.IMovementService;
import org.insurance.exception.InsuranceException;
import org.insurance.out.MovementDetailsOut;
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
	public ResponseWrapper<MovementDetailsOut> getCodeTable(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "Identifiant du mouvement", name = MOVEMENT_ID, required = true) @QueryParam("movementId") Long movementId)
			throws InsuranceException {
		ResponseWrapper<MovementDetailsOut> responseWrapper = new ResponseWrapper<MovementDetailsOut>();
		responseWrapper.setData(movementService.getMovement(userId, movementId));
		return responseWrapper;
	}

}
