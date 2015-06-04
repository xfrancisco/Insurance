package org.insurance.service.check.impl;

import javax.inject.Inject;

import org.insurance.conf.Cod_branch;
import org.insurance.conf.Cod_category;
import org.insurance.conf.Cod_guarantee;
import org.insurance.conf.Cod_premium;
import org.insurance.conf.Cod_section;
import org.insurance.exception.PremiumException;
import org.insurance.exception.PremiumException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IPremiumCheck;
import org.insurance.service.info.IPremiumInfo;

public class PremiumCheck extends ServiceCore implements IPremiumCheck {

	@Inject
	private IPremiumInfo premiumInfo;

	@Override
	public Cod_branch checkBranch(final String cbranch) throws PremiumException {
		Cod_branch codBranch = premiumInfo.getBranch(cbranch);
		if (codBranch == null)
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_UNKNWOWN_BRANCH, cbranch);
		return codBranch;

	}

	@Override
	public Cod_category checkCategory(final String cbranch, final String ccategory) throws PremiumException {
		Cod_category codCategory = premiumInfo.getCategory(ccategory);
		if (codCategory == null)
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_UNKNWOWN_CATEGORY, ccategory);

		codCategory = premiumInfo.getCategory(cbranch, ccategory);
		if (codCategory == null)
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_UNKNWOWN_BRANCH_CATEGORY, cbranch, ccategory);
		return codCategory;

	}

	@Override
	public Cod_premium checkPremium(String cbranch, String ccategory, String csection, String cguarantee, String cpremium) throws PremiumException {
		Cod_section codSection = premiumInfo.getSection(csection);
		if (codSection == null)
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_UNKNWOWN_SECTION, csection);

		Cod_guarantee codGuarantee = premiumInfo.getGuarantee(cguarantee);
		if (codGuarantee == null)
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_UNKNWOWN_GUARANTEE, cguarantee);

		Cod_premium codPremium = premiumInfo.getPremium(cpremium);
		if (codPremium == null)
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_UNKNWOWN_PREMIUM, cpremium);

		codPremium = premiumInfo.getPremium(cbranch, ccategory, csection, cguarantee, cpremium);
		if (codPremium == null)
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_UNKNWOWN_PREMIUM_CONF, cbranch, ccategory, csection, cguarantee, cpremium);
		return codPremium;
	}
}
