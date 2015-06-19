package org.mfi.common.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.mfi.common.ICodeTableService;
import org.mfi.conf.Cod_branch;
import org.mfi.conf.Cod_category;
import org.mfi.conf.Cod_duration;
import org.mfi.conf.Cod_guarantee;
import org.mfi.conf.Cod_premium;
import org.mfi.conf.Cod_section;
import org.mfi.conf.Cod_table;
import org.mfi.conf.Cod_tax;
import org.mfi.conf.Cod_version;
import org.mfi.exception.CodesException;
import org.mfi.exception.MfcException;
import org.mfi.in.CodeTableIn;
import org.mfi.out.QuoteStatusOut;
import org.mfi.out.codes.AllCodeTableOut;
import org.mfi.out.codes.CodeTableOut;
import org.mfi.out.codes.DurationOut;
import org.mfi.out.codes.EntityOut;
import org.mfi.out.codes.TaxOut;
import org.mfi.out.codes.VersionOut;
import org.mfi.service.check.ICodesCheck;
import org.mfi.service.check.IPremiumCheck;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.ICodesInfo;
import org.mfi.service.info.IPremiumInfo;
import org.mfi.service.info.IQuoteAndContractInfo;
import org.mfi.service.transactional.ICodesOperation;
import org.mfi.util.MappingUtils;
import org.mfi.utils.mapping.PremiumMapping;
import org.mfi.utils.mapping.QuoteMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class CodeTableService implements ICodeTableService {

	@Inject
	private ICodesInfo codesInfo;

	@Inject
	private ICodesCheck codesCheck;

	@Inject
	private IQuoteAndContractInfo quoteInfo;

	@Inject
	private IPremiumInfo premiumInfo;

	@Inject
	private IPremiumCheck premiumCheck;

	@Inject
	private IUserCheck userCheck;

	@Inject
	private ICodesOperation codesOperation;

	static final Logger logger = Logger.getLogger(CodeTableService.class);

	private static final String request = "SELECT {0}, {1}, indvali from {2}";

	@Override
	public List<CodeTableOut> getCodeTable(final String userId, final String codeTableName, final boolean allValues) throws MfcException {
		userCheck.checkUser(userId);
		Cod_table codTable = codesInfo.getCodeTable(codeTableName);
		if (codTable == null) {
			throw new CodesException(CodesException.ErrorCode.ERR_BIZ_CODES_UNKNOWN_TABLE, codeTableName);
		}

		String tablename = codTable.getTablename();
		String code = codTable.getTablecode();
		String label = codTable.getTablelabel();
		String query = MessageFormat.format(request, code, label, tablename);

		List<?> codeTableDatabaseList = codesInfo.getCodeTableList(query.trim(), allValues);
		return populate(codeTableDatabaseList, codTable);
	}

	@Override
	public VersionOut getVersion(final String userId) throws MfcException {
		userCheck.checkUser(userId);
		Cod_version version = codesInfo.getCurrentVersion();
		VersionOut result = new VersionOut();

		if (version != null) {
			//TODO XFR : Version applicative
			result.setApplicativeVersion("TODO");
			result.setDatabaseVersion(version.getLversion());
		}
		return result;

	}

	private List<CodeTableOut> populate(List<?> codeTableDatabaseList, Cod_table codTable) throws MfcException {
		List<CodeTableOut> codeTableListToReturn = new ArrayList<CodeTableOut>();
		Iterator<?> iter = codeTableDatabaseList.iterator();
		while (iter.hasNext()) {
			Object[] object = (Object[]) iter.next();
			CodeTableOut elem = new CodeTableOut();
			elem.setId((String) object[0]);
			elem.setLabel((String) object[1]);

			String indvali = (String) object[2];
			elem.setIsValid(MappingUtils.toBoolean(indvali));

			codeTableListToReturn.add(elem);
		}
		return codeTableListToReturn;
	}

	@Override
	public List<EntityOut> getBranches(final String userId) throws MfcException {
		userCheck.checkUser(userId);
		List<Cod_branch> branches = premiumInfo.getBranches();
		return PremiumMapping.populateBranchOutList(branches);
	}

	@Override
	public List<EntityOut> getCategories(final String userId, final String branchId) throws MfcException {
		userCheck.checkUser(userId);
		List<Cod_category> categories = premiumInfo.getCategories(branchId);
		List<EntityOut> result = PremiumMapping.populateCategoryOutList(categories);
		if (Strings.isNullOrEmpty(branchId)) {
			//TODO XFR
		}
		return result;
	}

	@Override
	public List<EntityOut> getSections(final String userId, final String branchId, final String categoryId) throws MfcException {
		userCheck.checkUser(userId);
		List<Cod_section> sections = premiumInfo.getSections(branchId, categoryId);
		List<EntityOut> result = PremiumMapping.populateSectionOutList(sections);
		if (Strings.isNullOrEmpty(categoryId)) {
			//TODO XFR
		}
		return result;
	}

	@Override
	public List<EntityOut> getGuarantees(final String userId, final String branchId, final String categoryId, final String sectionId)
			throws MfcException {
		userCheck.checkUser(userId);
		List<Cod_guarantee> guarantees = premiumInfo.getGuarantees(branchId, categoryId, sectionId);
		List<EntityOut> result = PremiumMapping.populateGuaranteeOutList(guarantees);
		if (Strings.isNullOrEmpty(sectionId)) {
			//TODO XFR
		}
		return result;
	}

	@Override
	public List<EntityOut> getPremiums(final String userId, final String branchId, final String categoryId, final String sectionId,
			final String guaranteeId) throws MfcException {
		userCheck.checkUser(userId);
		List<Cod_premium> premiums = premiumInfo.getPremiums(branchId, categoryId, sectionId, guaranteeId);
		List<EntityOut> result = PremiumMapping.populatePremiumOutList(premiums);
		if (Strings.isNullOrEmpty(guaranteeId)) {
			//TODO XFR
		}
		return result;
	}

	@Override
	public List<AllCodeTableOut> getAllCodes(final String userId) throws MfcException {
		userCheck.checkUser(userId);
		List<AllCodeTableOut> result = new ArrayList<AllCodeTableOut>();
		List<Cod_table> codTables = codesInfo.getCodeTables();

		for (Cod_table codTable : codTables) {
			AllCodeTableOut tmp = new AllCodeTableOut();
			tmp.setId(codTable.getCtable());
			tmp.setLabel(codTable.getLtable());
			tmp.setValid(MappingUtils.toBoolean(codTable.getIndvali()));

			String tablename = codTable.getTablename();
			String code = codTable.getTablecode();
			String label = codTable.getTablelabel();
			String query = MessageFormat.format(request, code, label, tablename);

			List<?> codeTableDatabaseList = codesInfo.getCodeTableList(query.trim(), true);
			tmp.setDetail(populate(codeTableDatabaseList, codTable));
			result.add(tmp);
		}

		AllCodeTableOut allCodeTableOut = new AllCodeTableOut(null, "QUOTESTATUS", true, getQuoteStatus(userId));
		result.add(allCodeTableOut);

		AllCodeTableOut allCodeTableOut2 = new AllCodeTableOut(null, "DURATIONS", true, getDurations(userId));
		result.add(allCodeTableOut2);

		return result;
	}

	@Override
	public List<QuoteStatusOut> getQuoteStatus(final String userId) throws MfcException {
		userCheck.checkUser(userId);
		return QuoteMapping.populateQuoteStatusOut(quoteInfo.getQuoteStatus());
	}

	@Override
	public List<AllCodeTableOut> updateCodeTables(final String userId, final List<CodeTableIn> codeIn) throws MfcException {
		userCheck.checkUser(userId);
		for (CodeTableIn codeTableIn : codeIn) {
			Cod_table codTable = codesCheck.checkCodeTable(codeTableIn.getCodeTableId());
			codesOperation.insertCodeTable(codTable, codeTableIn.getId(), codeTableIn.getLabel(), codeTableIn.getIsValid());
		}

		return getAllCodes(userId);
	}

	@Override
	public TaxOut getTax(final String userId, final String premiumId) throws MfcException {
		userCheck.checkUser(userId);
		Cod_premium codPremium = premiumCheck.checkPremium(premiumId);
		Cod_tax codTax = premiumCheck.checkTax(codPremium.getCtax());
		return populateTaxOut(codTax);
	}

	private TaxOut populateTaxOut(final Cod_tax codTax) {
		TaxOut result = new TaxOut();
		result.setId(codTax.getCtax());
		result.setLabel(codTax.getLtax());
		result.setValue(codTax.getTaxvalue());
		result.setIsValid(MappingUtils.toBoolean(codTax.getIndvali()));
		return result;
	}

	@Override
	public List<DurationOut> getDurations(String userId) throws MfcException {
		userCheck.checkUser(userId);
		List<Cod_duration> durations = codesInfo.getDurations();
		return populateDurationOut(durations);
	}

	private List<DurationOut> populateDurationOut(List<Cod_duration> durations) {
		List<DurationOut> result = new ArrayList<DurationOut>();
		for (Cod_duration codDuration : durations) {
			DurationOut durationOut = populateDurationOut(codDuration);
			result.add(durationOut);
		}
		return result;
	}

	private DurationOut populateDurationOut(Cod_duration codDuration) {
		DurationOut result = new DurationOut();
		result.setId(codDuration.getCduration());
		result.setIsValid(MappingUtils.toBoolean(codDuration.getIndvali()));
		result.setLabel(codDuration.getLduration());
		result.setTemporary(MappingUtils.toBoolean(codDuration.getIndtemporary()));
		return result;
	}
}
