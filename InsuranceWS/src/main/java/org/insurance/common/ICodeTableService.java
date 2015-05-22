package org.insurance.common;

import java.util.List;

import org.insurance.exception.InsuranceException;
import org.insurance.out.BranchOut;
import org.insurance.out.CategoryOut;
import org.insurance.out.CodeTableOut;
import org.insurance.out.GuaranteeOut;
import org.insurance.out.PremiumOut;
import org.insurance.out.SectionOut;
import org.insurance.out.VersionOut;

public interface ICodeTableService {

	VersionOut getVersion();

	List<CodeTableOut> getCodeTable(final String codeTableName, final boolean allValues) throws InsuranceException;

	List<BranchOut> getBranches();

	List<CategoryOut> getCategories(String branchId);

	List<SectionOut> getSections(String categoryId);

	List<GuaranteeOut> getGuarantees(String sectionId);

	List<PremiumOut> getPremiums(String guaranteeId);

}
