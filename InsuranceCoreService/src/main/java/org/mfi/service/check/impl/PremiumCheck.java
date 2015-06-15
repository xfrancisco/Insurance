package org.mfi.service.check.impl;

import javax.inject.Inject;

import org.mfi.conf.Cod_branch;
import org.mfi.conf.Cod_category;
import org.mfi.conf.Cod_guarantee;
import org.mfi.conf.Cod_premium;
import org.mfi.conf.Cod_section;
import org.mfi.conf.Cod_tax;
import org.mfi.exception.PremiumException;
import org.mfi.exception.PremiumException.ErrorCode;
import org.mfi.service.ServiceCore;
import org.mfi.service.check.IPremiumCheck;
import org.mfi.service.info.IPremiumInfo;

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

	@Override
	public Cod_premium checkPremium(String cpremium) throws PremiumException {
		Cod_premium codPremium = premiumInfo.getPremium(cpremium);
		if (codPremium == null)
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_UNKNWOWN_PREMIUM, cpremium);
		return codPremium;
	}

	@Override
	public Cod_tax checkTax(String ctax) throws PremiumException {
		Cod_tax codTax = premiumInfo.getTax(ctax);
		if (codTax == null)
			throw new PremiumException(ErrorCode.ERR_BIZ_PREMIUM_UNKNWOWN_TAX, ctax);
		return codTax;
	}
}
