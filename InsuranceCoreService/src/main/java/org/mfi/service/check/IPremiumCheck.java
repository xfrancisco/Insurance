package org.mfi.service.check;

import org.mfi.conf.Cod_branch;
import org.mfi.conf.Cod_category;
import org.mfi.conf.Cod_premium;
import org.mfi.conf.Cod_tax;
import org.mfi.exception.PremiumException;

public interface IPremiumCheck {

	Cod_branch checkBranch(String cbranch) throws PremiumException;

	Cod_category checkCategory(String cbranch, String ccategory) throws PremiumException;

	Cod_premium checkPremium(String cbranch, String ccategory, String csection, String cguarantee, String cpremium) throws PremiumException;

	Cod_premium checkPremium(String cpremium) throws PremiumException;

	Cod_tax checkTax(String ctax) throws PremiumException;;
}
