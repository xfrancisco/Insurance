package org.insurance.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.insurance.InsuranceEnterpriseModel.out.CodeTableOut;
import org.insurance.InsuranceEnterpriseModel.out.VersionOut;
import org.insurance.common.ICodeTableService;
import org.insurance.exception.InsuranceException;
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

}
