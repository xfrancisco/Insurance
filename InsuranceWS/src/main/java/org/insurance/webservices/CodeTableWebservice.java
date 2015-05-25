package org.insurance.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.insurance.common.ICodeTableService;
import org.insurance.exception.InsuranceException;
import org.insurance.out.CodeTableOut;
import org.insurance.out.EntityOut;
import org.insurance.out.VersionOut;
import org.springframework.stereotype.Controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/codeTable")
@Api(value = "codeTable", description = "Tables de code")
public class CodeTableWebservice extends AbstractWebservice {
	@Inject
	private ICodeTableService codeTableService;

	@GET
	@Path("/{codeTableName}")
	@ApiOperation(value = "Ensemble des valeurs pour une code donné")
	public ResponseWrapper<List<CodeTableOut>> getCodeTable(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId,
			@ApiParam(value = "codeTableName", required = true) @PathParam("codeTableName") String codeTableName) throws InsuranceException {
		ResponseWrapper<List<CodeTableOut>> responseWrapper = new ResponseWrapper<List<CodeTableOut>>();
		responseWrapper.setData(codeTableService.getCodeTable(userId, codeTableName, true));
		return responseWrapper;
	}

	@GET
	@Path("/version")
	@ApiOperation(value = "Version")
	public ResponseWrapper<VersionOut> getVersion(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId) {
		ResponseWrapper<VersionOut> responseWrapper = new ResponseWrapper<VersionOut>();
		VersionOut out = codeTableService.getVersion(userId);
		responseWrapper.setData(out);
		return responseWrapper;
	}

	@GET
	@Path("/branches")
	@ApiOperation(value = "Liste complète des branches")
	public ResponseWrapper<List<EntityOut>> getCodeTable(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getBranches(userId));
		return responseWrapper;
	}

	@GET
	@Path("/categories/{branchId}")
	@ApiOperation(value = "Liste des catégories filtrées par branch")
	public ResponseWrapper<List<EntityOut>> getCategories(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId,
			@ApiParam(value = "branchId", required = false) @PathParam("branchId") String branchId) throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getCategories(userId, branchId));
		return responseWrapper;
	}

	@GET
	@Path("/sections/{categoryId}")
	@ApiOperation(value = "Liste des sections filtrées par catégorie")
	public ResponseWrapper<List<EntityOut>> getSections(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId,
			@ApiParam(value = "categoryId", required = false) @PathParam("categoryId") String categoryId) throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getSections(userId, categoryId));
		return responseWrapper;
	}

	@GET
	@Path("/guarantees/{sectionId}")
	@ApiOperation(value = "Liste des garanties filtrées par section")
	public ResponseWrapper<List<EntityOut>> getGuarantees(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId,
			@ApiParam(value = "sectionId", required = false) @PathParam("sectionId") String sectionId) throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getGuarantees(userId, sectionId));
		return responseWrapper;
	}

	@GET
	@Path("/premiums/{guaranteeId}")
	@ApiOperation(value = "Liste des primes filtrées par garantie")
	public ResponseWrapper<List<EntityOut>> getPremiums(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId,
			@ApiParam(value = "guaranteeId", required = false) @PathParam("guaranteeId") String guaranteeId) throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getPremiums(userId, guaranteeId));
		return responseWrapper;
	}

	@GET
	@Path("/categories")
	@ApiOperation(value = "Liste complète des catégories")
	public ResponseWrapper<List<EntityOut>> getAllCategories(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getCategories(userId, null));
		return responseWrapper;
	}

	@GET
	@Path("/sections")
	@ApiOperation(value = "Liste complète des sections")
	public ResponseWrapper<List<EntityOut>> getAllSections(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getSections(userId, null));
		return responseWrapper;
	}

	@GET
	@Path("/guarantees")
	@ApiOperation(value = "Liste complète des garanties")
	public ResponseWrapper<List<EntityOut>> getAllGuarantees(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getGuarantees(userId, null));
		return responseWrapper;
	}

	@GET
	@Path("/premiums")
	@ApiOperation(value = "Liste complète des primes")
	public ResponseWrapper<List<EntityOut>> getAllPremiums(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @PathParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getPremiums(userId, null));
		return responseWrapper;
	}

}
