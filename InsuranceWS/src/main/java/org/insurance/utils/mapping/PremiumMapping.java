package org.insurance.utils.mapping;

import java.util.ArrayList;
import java.util.List;

import org.insurance.conf.Cod_branch;
import org.insurance.conf.Cod_category;
import org.insurance.conf.Cod_guarantee;
import org.insurance.conf.Cod_premium;
import org.insurance.conf.Cod_section;
import org.insurance.out.EntityOut;
import org.insurance.util.MappingUtils;

public final class PremiumMapping {

	public static EntityOut populateBranchOut(Cod_branch codBranch) {
		EntityOut result = new EntityOut();
		result.setId(codBranch.getCbranch());
		result.setLabel(codBranch.getLbranch());
		result.setIsValid(MappingUtils.toBoolean(codBranch.getIndvali()));
		return result;
	}

	public static List<EntityOut> populateBranchOutList(List<Cod_branch> branches) {
		List<EntityOut> result = new ArrayList<EntityOut>();
		for (Cod_branch codBranch : branches) {
			EntityOut tmp = populateBranchOut(codBranch);
			result.add(tmp);
		}
		return result;
	}

	public static EntityOut populateCategoryOut(Cod_category codCategory) {
		EntityOut result = new EntityOut();
		result.setId(codCategory.getCcategory());
		result.setLabel(codCategory.getLcategory());
		result.setIsValid(MappingUtils.toBoolean(codCategory.getIndvali()));
		return result;
	}

	public static List<EntityOut> populateCategoryOutList(List<Cod_category> categories) {
		List<EntityOut> result = new ArrayList<EntityOut>();
		for (Cod_category codCategory : categories) {
			EntityOut tmp = populateCategoryOut(codCategory);
			result.add(tmp);
		}
		return result;
	}

	public static EntityOut populateSectionOut(Cod_section codSection) {
		EntityOut result = new EntityOut();
		result.setId(codSection.getCsection());
		result.setLabel(codSection.getLsection());
		result.setIsValid(MappingUtils.toBoolean(codSection.getIndvali()));
		return result;
	}

	public static List<EntityOut> populateSectionOutList(List<Cod_section> sections) {
		List<EntityOut> result = new ArrayList<EntityOut>();
		for (Cod_section codSection : sections) {
			EntityOut tmp = populateSectionOut(codSection);
			result.add(tmp);
		}
		return result;
	}

	public static EntityOut populateGuaranteeOut(Cod_guarantee codGuarantee) {
		EntityOut result = new EntityOut();
		result.setId(codGuarantee.getCguarantee());
		result.setLabel(codGuarantee.getLguarantee());
		result.setIsValid(MappingUtils.toBoolean(codGuarantee.getIndvali()));
		return result;
	}

	public static List<EntityOut> populateGuaranteeOutList(List<Cod_guarantee> guarantees) {
		List<EntityOut> result = new ArrayList<EntityOut>();
		for (Cod_guarantee codGuarantee : guarantees) {
			EntityOut tmp = populateGuaranteeOut(codGuarantee);
			result.add(tmp);
		}
		return result;
	}

	public static EntityOut populatePremiumOut(Cod_premium codPremium) {
		EntityOut result = new EntityOut();
		result.setId(codPremium.getCpremium());
		result.setLabel(codPremium.getLpremium());
		result.setIsValid(MappingUtils.toBoolean(codPremium.getIndvali()));
		return result;
	}

	public static List<EntityOut> populatePremiumOutList(List<Cod_premium> premiums) {
		List<EntityOut> result = new ArrayList<EntityOut>();
		for (Cod_premium codPremium : premiums) {
			EntityOut tmp = populatePremiumOut(codPremium);
			result.add(tmp);
		}
		return result;
	}
}
