package org.mfi.common;

import java.util.List;

import org.mfi.exception.MfcException;
import org.mfi.in.CodeTableIn;
import org.mfi.out.AllCodeTableOut;
import org.mfi.out.CodeTableOut;
import org.mfi.out.EntityOut;
import org.mfi.out.QuoteStatusOut;
import org.mfi.out.TaxOut;
import org.mfi.out.VersionOut;

public interface ICodeTableService {

	VersionOut getVersion(final String userId) throws MfcException;

	List<CodeTableOut> getCodeTable(final String userId, final String codeTableName, final boolean allValues) throws MfcException;

	List<EntityOut> getBranches(final String userId) throws MfcException;

	List<EntityOut> getCategories(final String userId, String branchId) throws MfcException;

	List<EntityOut> getSections(final String userId, String branchId, String categoryId) throws MfcException;

	List<EntityOut> getGuarantees(final String userId, String branchId, String categoryId, String sectionId) throws MfcException;

	List<EntityOut> getPremiums(final String userId, String branchId, String categoryId, String sectionId, String guaranteeId)
			throws MfcException;

	List<AllCodeTableOut> getAllCodes(final String userId) throws MfcException;

	List<QuoteStatusOut> getQuoteStatus(final String userId) throws MfcException;

	List<AllCodeTableOut> updateCodeTables(final String userId, final List<CodeTableIn> codeIn) throws MfcException;

	TaxOut getTax(String userId, String premiumId) throws MfcException;

}
