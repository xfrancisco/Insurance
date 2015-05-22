package org.insurance.common.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.insurance.common.ICodeTableService;
import org.insurance.conf.Cod_branch;
import org.insurance.conf.Cod_category;
import org.insurance.conf.Cod_guarantee;
import org.insurance.conf.Cod_premium;
import org.insurance.conf.Cod_section;
import org.insurance.conf.Cod_version;
import org.insurance.exception.CodesException;
import org.insurance.exception.InsuranceException;
import org.insurance.exception.TechnicalException;
import org.insurance.exception.TechnicalException.ErrorCode;
import org.insurance.out.BranchOut;
import org.insurance.out.CategoryOut;
import org.insurance.out.CodeTableOut;
import org.insurance.out.GuaranteeOut;
import org.insurance.out.PremiumOut;
import org.insurance.out.SectionOut;
import org.insurance.out.VersionOut;
import org.insurance.service.info.ICodesInfo;
import org.insurance.service.info.IPremiumInfo;
import org.insurance.util.MappingUtils;
import org.insurance.utils.mapping.PremiumMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class CodeTableService implements ICodeTableService {

	@Inject
	private ICodesInfo codesInfo;

	@Inject
	private IPremiumInfo premiumInfo;

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

	@Override
	public List<BranchOut> getBranches() {
		List<Cod_branch> branches = premiumInfo.getBranches();
		return PremiumMapping.populateBranchOutList(branches);
	}

	@Override
	public List<CategoryOut> getCategories(String branchId) {
		List<Cod_category> categories = premiumInfo.getCategories(branchId);
		List<CategoryOut> result = PremiumMapping.populateCategoryOutList(categories);
		if (Strings.isNullOrEmpty(branchId)) {
			//TODO XFR
		}
		return result;
	}

	@Override
	public List<SectionOut> getSections(String categoryId) {
		List<Cod_section> sections = premiumInfo.getSections(categoryId);
		List<SectionOut> result = PremiumMapping.populateSectionOutList(sections);
		if (Strings.isNullOrEmpty(categoryId)) {
			//TODO XFR
		}
		return result;
	}

	@Override
	public List<GuaranteeOut> getGuarantees(String sectionId) {
		List<Cod_guarantee> guarantees = premiumInfo.getGuarantees(sectionId);
		List<GuaranteeOut> result = PremiumMapping.populateGuaranteeOutList(guarantees);
		if (Strings.isNullOrEmpty(sectionId)) {
			//TODO XFR
		}
		return result;
	}

	@Override
	public List<PremiumOut> getPremiums(String guaranteeId) {
		List<Cod_premium> premiums = premiumInfo.getPremiums(guaranteeId);
		List<PremiumOut> result = PremiumMapping.populatePremiumOutList(premiums);
		if (Strings.isNullOrEmpty(guaranteeId)) {
			//TODO XFR
		}
		return result;
	}

}
