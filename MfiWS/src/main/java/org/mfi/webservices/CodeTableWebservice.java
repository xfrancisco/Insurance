package org.mfi.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.mfi.common.ICodeTableService;
import org.mfi.exception.MfcException;
import org.mfi.in.CodeTableIn;
import org.mfi.out.AllCodeTableOut;
import org.mfi.out.CodeTableOut;
import org.mfi.out.DurationOut;
import org.mfi.out.EntityOut;
import org.mfi.out.QuoteStatusOut;
import org.mfi.out.TaxOut;
import org.mfi.out.VersionOut;
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
			@ApiParam(value = "Nom de la code table", name = CODETABLE_NAME, required = true) @QueryParam(CODETABLE_NAME) String codeTableName)
			throws MfcException {
		ResponseWrapper<List<CodeTableOut>> responseWrapper = new ResponseWrapper<List<CodeTableOut>>();
		responseWrapper.setData(codeTableService.getCodeTable(userId, codeTableName, true));
		return responseWrapper;
	}

	@GET
	@Path("/list")
	@ApiOperation(value = "Liste des codes table")
	public ResponseWrapper<List<AllCodeTableOut>> getAllCodes(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws MfcException {
		ResponseWrapper<List<AllCodeTableOut>> responseWrapper = new ResponseWrapper<List<AllCodeTableOut>>();
		responseWrapper.setData(codeTableService.getAllCodes(userId));
		return responseWrapper;
	}

	@GET
	@Path("/version")
	@ApiOperation(value = "Version")
	public ResponseWrapper<VersionOut> getVersion(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws MfcException {
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
			throws MfcException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getBranches(userId));
		return responseWrapper;
	}

	@GET
	@Path("/categories/filter")
	@ApiOperation(value = "Liste des catégories filtrées par branche")
	public ResponseWrapper<List<EntityOut>> getCategories(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Identifiant de la branche", name = BRANCH_ID) @QueryParam(BRANCH_ID) String branchId)
			throws MfcException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getCategories(userId, branchId));
		return responseWrapper;
	}

	@GET
	@Path("/sections/filter")
	@ApiOperation(value = "Liste des sections filtrées par branche et catégorie")
	public ResponseWrapper<List<EntityOut>> getSections(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Identifiant de la branche", name = BRANCH_ID) @QueryParam(BRANCH_ID) String branchId,
			@ApiParam(required = true, value = "Identifiant de la catégorie", name = CATEGORY_ID) @QueryParam(CATEGORY_ID) String categoryId)
			throws MfcException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getSections(userId, branchId, categoryId));
		return responseWrapper;
	}

	@GET
	@Path("/guarantees/filter")
	@ApiOperation(value = "Liste des garanties filtrées par branche, catégorie et section")
	public ResponseWrapper<List<EntityOut>> getGuarantees(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Identifiant de la branche", name = BRANCH_ID) @QueryParam(BRANCH_ID) String branchId,
			@ApiParam(required = true, value = "Identifiant de la catégorie", name = CATEGORY_ID) @QueryParam(CATEGORY_ID) String categoryId,
			@ApiParam(required = true, value = "Identifiant de la section", name = SECTION_ID) @QueryParam(SECTION_ID) String sectionId)
			throws MfcException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getGuarantees(userId, branchId, categoryId, sectionId));
		return responseWrapper;
	}

	@GET
	@Path("/premiums/filter")
	@ApiOperation(value = "Liste des primes filtrées par branche, catégorie, section et garantie")
	public ResponseWrapper<List<EntityOut>> getPremiums(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Identifiant de la branche", name = BRANCH_ID) @QueryParam(BRANCH_ID) String branchId,
			@ApiParam(required = true, value = "Identifiant de la catégorie", name = CATEGORY_ID) @QueryParam(CATEGORY_ID) String categoryId,
			@ApiParam(required = true, value = "Identifiant de la section", name = SECTION_ID) @QueryParam(SECTION_ID) String sectionId,
			@ApiParam(required = true, value = "Identifiant de la garantie", name = GUARANTEE_ID) @QueryParam(GUARANTEE_ID) String guaranteeId)
			throws MfcException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getPremiums(userId, branchId, categoryId, sectionId, guaranteeId));
		return responseWrapper;
	}

	@GET
	@Path("/categories")
	@ApiOperation(value = "Liste complète des catégories")
	public ResponseWrapper<List<EntityOut>> getAllCategories(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws MfcException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getCategories(userId, null));
		return responseWrapper;
	}

	@GET
	@Path("/sections")
	@ApiOperation(value = "Liste complète des sections")
	public ResponseWrapper<List<EntityOut>> getAllSections(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws MfcException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getSections(userId, null, null));
		return responseWrapper;
	}

	@GET
	@Path("/guarantees")
	@ApiOperation(value = "Liste complète des garanties")
	public ResponseWrapper<List<EntityOut>> getAllGuarantees(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws MfcException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getGuarantees(userId, null, null, null));
		return responseWrapper;
	}

	@GET
	@Path("/premiums")
	@ApiOperation(value = "Liste complète des primes")
	public ResponseWrapper<List<EntityOut>> getAllPremiums(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws MfcException {
		ResponseWrapper<List<EntityOut>> responseWrapper = new ResponseWrapper<List<EntityOut>>();
		responseWrapper.setData(codeTableService.getPremiums(userId, null, null, null, null));
		return responseWrapper;
	}

	@GET
	@Path("/taxes")
	@ApiOperation(value = "Taux de taxe par prime")
	public ResponseWrapper<TaxOut> getTax(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Identifiant de la prime", name = PREMIUM_ID) @QueryParam(PREMIUM_ID) String premiumId)
			throws MfcException {
		ResponseWrapper<TaxOut> responseWrapper = new ResponseWrapper<TaxOut>();
		responseWrapper.setData(codeTableService.getTax(userId, premiumId));
		return responseWrapper;
	}

	@GET
	@Path("/quotestatus")
	@ApiOperation(value = "Liste des statuts des devis")
	public ResponseWrapper<List<QuoteStatusOut>> getQuoteStatus(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws MfcException {
		ResponseWrapper<List<QuoteStatusOut>> responseWrapper = new ResponseWrapper<List<QuoteStatusOut>>();
		responseWrapper.setData(codeTableService.getQuoteStatus(userId));
		return responseWrapper;
	}

	@POST
	@Path("/edit")
	@ApiOperation(value = "Paramétrage des codes simples (code/libellé/validité)")
	public ResponseWrapper<List<AllCodeTableOut>> edit(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid List<CodeTableIn> codeIn) throws MfcException {
		ResponseWrapper<List<AllCodeTableOut>> responseWrapper = new ResponseWrapper<List<AllCodeTableOut>>();
		responseWrapper.setData(codeTableService.updateCodeTables(userId, codeIn));
		return responseWrapper;
	}

	@GET
	@Path("/durations")
	@ApiOperation(value = "Durées")
	public ResponseWrapper<List<DurationOut>> getTax(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId)
			throws MfcException {
		ResponseWrapper<List<DurationOut>> responseWrapper = new ResponseWrapper<List<DurationOut>>();
		responseWrapper.setData(codeTableService.getDurations(userId));
		return responseWrapper;
	}

}
