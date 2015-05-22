package org.insurance.utils.mapping;

import java.util.ArrayList;
import java.util.List;

import org.insurance.conf.Cod_branch;
import org.insurance.conf.Cod_category;
import org.insurance.conf.Cod_guarantee;
import org.insurance.conf.Cod_premium;
import org.insurance.conf.Cod_section;
import org.insurance.out.BranchOut;
import org.insurance.out.CategoryOut;
import org.insurance.out.GuaranteeOut;
import org.insurance.out.PremiumOut;
import org.insurance.out.SectionOut;
import org.insurance.util.MappingUtils;

public final class PremiumMapping {

	public static BranchOut populateBranchOut(Cod_branch codBranch) {
		BranchOut result = new BranchOut();
		result.setBranchId(codBranch.getCbranch());
		result.setBranchLabel(codBranch.getLbranch());
		result.setValid(MappingUtils.toBoolean(codBranch.getIndvali()));
		return result;
	}

	public static List<BranchOut> populateBranchOutList(List<Cod_branch> branches) {
		List<BranchOut> result = new ArrayList<BranchOut>();
		for (Cod_branch codBranch : branches) {
			BranchOut tmp = populateBranchOut(codBranch);
			result.add(tmp);
		}
		return result;
	}

	public static CategoryOut populateCategoryOut(Cod_category codCategory) {
		CategoryOut result = new CategoryOut();
		result.setCategoryId(codCategory.getCcategory());
		result.setCategoryLabel(codCategory.getLcategory());
		result.setValid(MappingUtils.toBoolean(codCategory.getIndvali()));
		return result;
	}

	public static List<CategoryOut> populateCategoryOutList(List<Cod_category> categories) {
		List<CategoryOut> result = new ArrayList<CategoryOut>();
		for (Cod_category codCategory : categories) {
			CategoryOut tmp = populateCategoryOut(codCategory);
			result.add(tmp);
		}
		return result;
	}

	public static SectionOut populateSectionOut(Cod_section codSection) {
		SectionOut result = new SectionOut();
		result.setSectionId(codSection.getCsection());
		result.setSectionLabel(codSection.getLsection());
		result.setValid(MappingUtils.toBoolean(codSection.getIndvali()));
		return result;
	}

	public static List<SectionOut> populateSectionOutList(List<Cod_section> sections) {
		List<SectionOut> result = new ArrayList<SectionOut>();
		for (Cod_section codSection : sections) {
			SectionOut tmp = populateSectionOut(codSection);
			result.add(tmp);
		}
		return result;
	}

	public static GuaranteeOut populateGuaranteeOut(Cod_guarantee codGuarantee) {
		GuaranteeOut result = new GuaranteeOut();
		result.setGuaranteeId(codGuarantee.getCguarantee());
		result.setGuaranteeLabel(codGuarantee.getLguarantee());
		result.setValid(MappingUtils.toBoolean(codGuarantee.getIndvali()));
		return result;
	}

	public static List<GuaranteeOut> populateGuaranteeOutList(List<Cod_guarantee> guarantees) {
		List<GuaranteeOut> result = new ArrayList<GuaranteeOut>();
		for (Cod_guarantee codGuarantee : guarantees) {
			GuaranteeOut tmp = populateGuaranteeOut(codGuarantee);
			result.add(tmp);
		}
		return result;
	}

	public static PremiumOut populatePremiumOut(Cod_premium codPremium) {
		PremiumOut result = new PremiumOut();
		result.setPremiumId(codPremium.getCpremium());
		result.setPremiumLabel(codPremium.getLpremium());
		result.setValid(MappingUtils.toBoolean(codPremium.getIndvali()));
		return result;
	}

	public static List<PremiumOut> populatePremiumOutList(List<Cod_premium> premiums) {
		List<PremiumOut> result = new ArrayList<PremiumOut>();
		for (Cod_premium codPremium : premiums) {
			PremiumOut tmp = populatePremiumOut(codPremium);
			result.add(tmp);
		}
		return result;
	}
}
