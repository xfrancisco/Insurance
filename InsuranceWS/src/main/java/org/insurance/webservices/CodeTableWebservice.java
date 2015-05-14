package org.insurance.webservices;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.insurance.InsuranceEnterpriseModel.out.VersionOut;
import org.insurance.common.ICodeTableService;
import org.springframework.stereotype.Controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@Path("/codeTable")
@Api(value = "codeTable", description = "Tables de code")
public class CodeTableWebservice {
	@Inject
	private ICodeTableService codeTableService;

	/*@GET
	@Path("/{codeTableName}")
	@ApiOperation(value="Ensemble des valeurs pour une code donné")
	public ResponseWrapper<List<CodeTableOut>> getCodeTable(@ApiParam(value="codeTableName",required=true)@PathParam("codeTableName") String codeTableName ) {
		CodeTableService codeTableService = new CodeTableService();
		ResponseWrapper<List<CodeTableOut>> responseWrapper = new ResponseWrapper<List<CodeTableOut>>();
		responseWrapper.setData(codeTableService.getCodeTable(codeTableName));
		return responseWrapper;
	}*/

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
