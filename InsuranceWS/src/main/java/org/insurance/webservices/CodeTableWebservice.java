package org.insurance.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.insurance.common.ICodeTableService;
import org.insurance.exception.InsuranceException;
import org.insurance.out.BranchOut;
import org.insurance.out.CategoryOut;
import org.insurance.out.CodeTableOut;
import org.insurance.out.GuaranteeOut;
import org.insurance.out.PremiumOut;
import org.insurance.out.SectionOut;
import org.insurance.out.VersionOut;
import org.springframework.stereotype.Controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/codeTable")
@Api(value = "codeTable", description = "Tables de code")
public class CodeTableWebservice {
	@Inject
	private ICodeTableService codeTableService;

	@GET
	@Path("/{codeTableName}")
	@ApiOperation(value = "Ensemble des valeurs pour une code donné")
	public ResponseWrapper<List<CodeTableOut>> getCodeTable(
			@ApiParam(value = "codeTableName", required = true) @PathParam("codeTableName") String codeTableName) throws InsuranceException {
		ResponseWrapper<List<CodeTableOut>> responseWrapper = new ResponseWrapper<List<CodeTableOut>>();
		responseWrapper.setData(codeTableService.getCodeTable(codeTableName, true));
		return responseWrapper;
	}

	@GET
	@Path("/version")
	@ApiOperation(value = "Version")
	public ResponseWrapper<VersionOut> getVersion() {
		ResponseWrapper<VersionOut> responseWrapper = new ResponseWrapper<VersionOut>();
		VersionOut out = codeTableService.getVersion();
		responseWrapper.setData(out);
		return responseWrapper;
	}

	@GET
	@Path("/branches")
	@ApiOperation(value = "Liste complète des branches")
	public ResponseWrapper<List<BranchOut>> getCodeTable() throws InsuranceException {
		ResponseWrapper<List<BranchOut>> responseWrapper = new ResponseWrapper<List<BranchOut>>();
		responseWrapper.setData(codeTableService.getBranches());
		return responseWrapper;
	}

	@GET
	@Path("/categories/{branchId}")
	@ApiOperation(value = "Liste des catégories filtrées par branch")
	public ResponseWrapper<List<CategoryOut>> getCategories(@ApiParam(value = "branchId", required = false) @PathParam("branchId") String branchId)
			throws InsuranceException {
		ResponseWrapper<List<CategoryOut>> responseWrapper = new ResponseWrapper<List<CategoryOut>>();
		responseWrapper.setData(codeTableService.getCategories(branchId));
		return responseWrapper;
	}

	@GET
	@Path("/sections/{categoryId}")
	@ApiOperation(value = "Liste des sections filtrées par catégorie")
	public ResponseWrapper<List<SectionOut>> getSections(@ApiParam(value = "categoryId", required = false) @PathParam("categoryId") String categoryId)
			throws InsuranceException {
		ResponseWrapper<List<SectionOut>> responseWrapper = new ResponseWrapper<List<SectionOut>>();
		responseWrapper.setData(codeTableService.getSections(categoryId));
		return responseWrapper;
	}

	@GET
	@Path("/guarantees/{sectionId}")
	@ApiOperation(value = "Liste des garanties filtrées par section")
	public ResponseWrapper<List<GuaranteeOut>> getGuarantees(@ApiParam(value = "sectionId", required = false) @PathParam("sectionId") String sectionId)
			throws InsuranceException {
		ResponseWrapper<List<GuaranteeOut>> responseWrapper = new ResponseWrapper<List<GuaranteeOut>>();
		responseWrapper.setData(codeTableService.getGuarantees(sectionId));
		return responseWrapper;
	}

	@GET
	@Path("/premiums/{guaranteeId}")
	@ApiOperation(value = "Liste des primes filtrées par garantie")
	public ResponseWrapper<List<PremiumOut>> getPremiums(
			@ApiParam(value = "guaranteeId", required = false) @PathParam("guaranteeId") String guaranteeId) throws InsuranceException {
		ResponseWrapper<List<PremiumOut>> responseWrapper = new ResponseWrapper<List<PremiumOut>>();
		responseWrapper.setData(codeTableService.getPremiums(guaranteeId));
		return responseWrapper;
	}

	@GET
	@Path("/categories")
	@ApiOperation(value = "Liste complète des catégories")
	public ResponseWrapper<List<CategoryOut>> getAllCategories() throws InsuranceException {
		ResponseWrapper<List<CategoryOut>> responseWrapper = new ResponseWrapper<List<CategoryOut>>();
		responseWrapper.setData(codeTableService.getCategories(null));
		return responseWrapper;
	}

	@GET
	@Path("/sections")
	@ApiOperation(value = "Liste complète des sections")
	public ResponseWrapper<List<SectionOut>> getAllSections() throws InsuranceException {
		ResponseWrapper<List<SectionOut>> responseWrapper = new ResponseWrapper<List<SectionOut>>();
		responseWrapper.setData(codeTableService.getSections(null));
		return responseWrapper;
	}

	@GET
	@Path("/guarantees")
	@ApiOperation(value = "Liste complète des garanties")
	public ResponseWrapper<List<GuaranteeOut>> getAllGuarantees() throws InsuranceException {
		ResponseWrapper<List<GuaranteeOut>> responseWrapper = new ResponseWrapper<List<GuaranteeOut>>();
		responseWrapper.setData(codeTableService.getGuarantees(null));
		return responseWrapper;
	}

	@GET
	@Path("/premiums")
	@ApiOperation(value = "Liste complète des primes")
	public ResponseWrapper<List<PremiumOut>> getAllPremiums() throws InsuranceException {
		ResponseWrapper<List<PremiumOut>> responseWrapper = new ResponseWrapper<List<PremiumOut>>();
		responseWrapper.setData(codeTableService.getPremiums(null));
		return responseWrapper;
	}

}
