package org.insurance.common;

import java.util.List;

import org.insurance.exception.InsuranceException;
import org.insurance.in.CodeTableIn;
import org.insurance.out.AllCodeTableOut;
import org.insurance.out.CodeTableOut;
import org.insurance.out.EntityOut;
import org.insurance.out.QuoteStatusOut;
import org.insurance.out.TaxOut;
import org.insurance.out.VersionOut;

public interface ICodeTableService {

	VersionOut getVersion(final String userId) throws InsuranceException;

	List<CodeTableOut> getCodeTable(final String userId, final String codeTableName, final boolean allValues) throws InsuranceException;

	List<EntityOut> getBranches(final String userId) throws InsuranceException;

	List<EntityOut> getCategories(final String userId, String branchId) throws InsuranceException;

	List<EntityOut> getSections(final String userId, String categoryId) throws InsuranceException;

	List<EntityOut> getGuarantees(final String userId, String sectionId) throws InsuranceException;

	List<EntityOut> getPremiums(final String userId, String guaranteeId) throws InsuranceException;

	List<AllCodeTableOut> getAllCodes(final String userId) throws InsuranceException;

	List<QuoteStatusOut> getQuoteStatus(final String userId) throws InsuranceException;

	List<AllCodeTableOut> updateCodeTables(final String userId, final List<CodeTableIn> codeIn) throws InsuranceException;

	TaxOut getTax(String userId, String premiumId) throws InsuranceException;

}
