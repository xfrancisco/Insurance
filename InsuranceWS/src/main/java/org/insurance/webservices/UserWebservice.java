package org.insurance.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.insurance.common.IUserService;
import org.insurance.exception.InsuranceException;
import org.insurance.in.UserIn;
import org.insurance.out.UserOut;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/user")
@Api(value = "user", description = "Utilisateurs")
@Validated
public class UserWebservice extends AbstractWebservice {

	@Inject
	private IUserService userService;

	@POST
	@Path("/create")
	@ApiOperation(value = "Création d'un utilisateur")
	public ResponseWrapper<UserOut> insertUser(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid UserIn userIn) throws InsuranceException {
		ResponseWrapper<UserOut> responseWrapper = new ResponseWrapper<UserOut>();
		responseWrapper.setData(userService.insertUser(userId, userIn));
		return responseWrapper;
	}

	@PUT
	@Path("/update")
	@ApiOperation(value = "Mise à jour d'un utilisateur")
	public ResponseWrapper<UserOut> updateUser(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid UserIn userIn) throws InsuranceException {
		ResponseWrapper<UserOut> responseWrapper = new ResponseWrapper<UserOut>();
		responseWrapper.setData(userService.updateUser(userId, userIn));
		return responseWrapper;
	}

	@GET
	@Path("/list")
	@ApiOperation(value = "Liste des utilisateurs")
	public ResponseWrapper<List<UserOut>> getUsers(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<UserOut>> responseWrapper = new ResponseWrapper<List<UserOut>>();
		responseWrapper.setData(userService.getUsers(userId));
		return responseWrapper;
	}

}
