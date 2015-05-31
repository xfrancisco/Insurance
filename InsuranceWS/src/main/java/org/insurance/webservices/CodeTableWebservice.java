package org.insurance.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.insurance.common.ICodeTableService;
import org.insurance.exception.InsuranceException;
import org.insurance.out.AllCodeTableOut;
import org.insurance.out.CodeTableOut;
import org.insurance.out.EntityOut;
import org.insurance.out.QuoteStatusOut;
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
	@Path("/codeTable")
	@ApiOperation(value = "Ensemble des valeurs pour une code donné")
	public ResponseWrapper<List<CodeTableOut>> getCodeTable(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "codeTableName", required = true) @QueryParam("codeTableName") String codeTableName) throws InsuranceException {
		ResponseWrapper<List<CodeTableOut>> responseWrapper = new ResponseWrapper<List<CodeTableOut>>();
		responseWrapper.setData(codeTableService.getCodeTable(userId, codeTableName, true));
		return responseWrapper;
	}

	@GET
	@Path("/codeTable/all")
	@ApiOperation(value = "Liste des codes table")
	public ResponseWrapper<List<AllCodeTableOut>> getAllCodes(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<AllCodeTableOut>> responseWrapper = new ResponseWrapper<List<AllCodeTableOut>>();
		responseWrapper.setData(codeTableService.getAllCodes(userId));
		return responseWrapper;
	}

	@GET
	@Path("/version")
	@ApiOperation(value = "Version")
	public ResponseWrapper<VersionOut> getVersion(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<VersionOut> responseWrapper = new ResponseWrapper<VersionOut>();
		VersionOut out = codeTableService.getVersion(userId);
		responseWrapper.setData(out);
		return responseWrapper;
	}

	@GET
	@Path("/branches")
	@ApiOperation(value = "Liste complète des branches")
	public ResponseWrapper<List<EntityOut>> getCodeTable(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getBranches(userId));
		return responseWrapper;
	}

	@GET
	@Path("/categories/filter")
	@ApiOperation(value = "Liste des catégories filtrées par branche")
	public ResponseWrapper<List<EntityOut>> getCategories(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "branchId", required = false) @QueryParam("branchId") String branchId) throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getCategories(userId, branchId));
		return responseWrapper;
	}

	@GET
	@Path("/sections/filter")
	@ApiOperation(value = "Liste des sections filtrées par catégorie")
	public ResponseWrapper<List<EntityOut>> getSections(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "categoryId", required = false) @QueryParam("categoryId") String categoryId) throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getSections(userId, categoryId));
		return responseWrapper;
	}

	@GET
	@Path("/guarantees/filter")
	@ApiOperation(value = "Liste des garanties filtrées par section")
	public ResponseWrapper<List<EntityOut>> getGuarantees(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "sectionId", required = false) @QueryParam("sectionId") String sectionId) throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getGuarantees(userId, sectionId));
		return responseWrapper;
	}

	@GET
	@Path("/premiums/filter")
	@ApiOperation(value = "Liste des primes filtrées par garantie")
	public ResponseWrapper<List<EntityOut>> getPremiums(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(value = "guaranteeId", required = false) @QueryParam("guaranteeId") String guaranteeId) throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getPremiums(userId, guaranteeId));
		return responseWrapper;
	}

	@GET
	@Path("/categories")
	@ApiOperation(value = "Liste complète des catégories")
	public ResponseWrapper<List<EntityOut>> getAllCategories(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getCategories(userId, null));
		return responseWrapper;
	}

	@GET
	@Path("/sections")
	@ApiOperation(value = "Liste complète des sections")
	public ResponseWrapper<List<EntityOut>> getAllSections(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getSections(userId, null));
		return responseWrapper;
	}

	@GET
	@Path("/guarantees")
	@ApiOperation(value = "Liste complète des garanties")
	public ResponseWrapper<List<EntityOut>> getAllGuarantees(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getGuarantees(userId, null));
		return responseWrapper;
	}

	@GET
	@Path("/premiums")
	@ApiOperation(value = "Liste complète des primes")
	public ResponseWrapper<List<EntityOut>> getAllPremiums(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getPremiums(userId, null));
		return responseWrapper;
	}

	@GET
	@Path("/quotestatus")
	@ApiOperation(value = "Liste des statuts des devis")
	public ResponseWrapper<List<QuoteStatusOut>> getQuoteStatus(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws InsuranceException {
		ResponseWrapper<List<QuoteStatusOut>> responseWrapper = new ResponseWrapper<List<QuoteStatusOut>>();
		responseWrapper.setData(codeTableService.getQuoteStatus(userId));
		return responseWrapper;
	}

}
