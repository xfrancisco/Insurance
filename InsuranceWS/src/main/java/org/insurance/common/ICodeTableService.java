package org.insurance.common;

import java.util.List;

import org.insurance.InsuranceEnterpriseModel.out.CodeTableOut;
import org.insurance.InsuranceEnterpriseModel.out.VersionOut;
import org.insurance.exception.InsuranceException;

public interface ICodeTableService {

	VersionOut getVersion();

	List<CodeTableOut> getCodeTable(String codeTableName, boolean allValues) throws InsuranceException;

}
