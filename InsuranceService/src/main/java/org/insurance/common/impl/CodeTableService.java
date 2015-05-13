package org.insurance.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.InsuranceEnterpriseModel.out.CodeTableOut;
import org.insurance.InsuranceEnterpriseModel.out.VersionOut;
import org.insurance.common.ICodeTableService;
import org.insurance.dao.IGenericDao;
import org.insurance.version.Cod_version;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class CodeTableService implements ICodeTableService {

	@Inject
	private IGenericDao genericDao;

	static final Logger logger = Logger.getLogger(CodeTableService.class);

	public List<CodeTableOut> getCodeTable(String codeTableName) {
		List<CodeTableOut> result = new ArrayList<CodeTableOut>();
		CodeTableOut tmp = new CodeTableOut();
		tmp.setCode(codeTableName);
		tmp.setLabel("label");
		result.add(tmp);
		return result;
	}

	@Override
	public VersionOut getVersion() {

		Cod_version tmp = genericDao.get(Cod_version.class, "0.0.1");
		VersionOut result = new VersionOut();
		result.setApplicativeVersion("TODO");
		result.setDatabaseVersion(tmp.getLversion());
		return result;

	}
}
