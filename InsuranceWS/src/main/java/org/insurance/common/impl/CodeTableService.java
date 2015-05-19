package org.insurance.common.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.insurance.InsuranceEnterpriseModel.out.CodeTableOut;
import org.insurance.InsuranceEnterpriseModel.out.VersionOut;
import org.insurance.common.ICodeTableService;
import org.insurance.conf.Cod_version;
import org.insurance.exception.CodesException;
import org.insurance.exception.InsuranceException;
import org.insurance.exception.TechnicalException;
import org.insurance.exception.TechnicalException.ErrorCode;
import org.insurance.service.info.ICodesInfo;
import org.insurance.util.MappingUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class CodeTableService implements ICodeTableService {

	@Inject
	private ICodesInfo codesInfo;

	@Resource(name = "codeTables")
	private Properties codeTablesProperties;

	static final Logger logger = Logger.getLogger(CodeTableService.class);

	private static final String QUERY_SUFFIX = ".query";
	private static final String CODE_SUFFIX = ".code";
	private static final String LABEL_SUFFIX = ".label";

	@Override
	public List<CodeTableOut> getCodeTable(final String codeTableName, final boolean allValues) throws InsuranceException {
		String tablename = codeTableName;
		String query = codeTablesProperties.getProperty(tablename + QUERY_SUFFIX);
		if (query == null) {
			tablename = codeTableName.toLowerCase();
			query = codeTablesProperties.getProperty(tablename + QUERY_SUFFIX);
		}
		if (Strings.isNullOrEmpty(query)) {
			throw new CodesException(CodesException.ErrorCode.ERR_BIZ_CODES_UNKNOWN_TABLE, codeTableName);
		}
		List<?> codeTableDatabaseList = codesInfo.getCodeTableList(query.trim(), allValues);
		return populate(codeTableDatabaseList, tablename);
	}

	@Override
	public VersionOut getVersion() {
		Cod_version version = codesInfo.getCurrentVersion();
		VersionOut result = new VersionOut();

		if (version != null) {
			//TODO XFR : Version applicative
			result.setApplicativeVersion("TODO");
			result.setDatabaseVersion(version.getLversion());
		}
		return result;

	}

	private List<CodeTableOut> populate(List<?> codeTableDatabaseList, String codeTableName) throws InsuranceException {
		List<CodeTableOut> codeTableListToReturn = new ArrayList<CodeTableOut>();
		for (Object obj : codeTableDatabaseList) {
			CodeTableOut elem = new CodeTableOut();
			try {
				elem.setCode(BeanUtils.getProperty(obj, codeTablesProperties.getProperty(codeTableName + CODE_SUFFIX)));
				elem.setLabel(BeanUtils.getProperty(obj, codeTablesProperties.getProperty(codeTableName + LABEL_SUFFIX)));
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new TechnicalException(ErrorCode.ERR_TECH_CODETABLE, e);
			}

			try {
				String indvali = BeanUtils.getProperty(obj, "indvali");
				elem.setIsValid(MappingUtils.toBoolean(indvali));
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				elem.setIsValid(true);
			}

			codeTableListToReturn.add(elem);
		}
		return codeTableListToReturn;
	}
}
