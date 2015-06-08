package org.insurance.service.check;

import org.insurance.conf.Cod_branch;
import org.insurance.conf.Cod_category;
import org.insurance.conf.Cod_premium;
import org.insurance.conf.Cod_tax;
import org.insurance.exception.PremiumException;

public interface IPremiumCheck {

	Cod_branch checkBranch(String cbranch) throws PremiumException;

	Cod_category checkCategory(String cbranch, String ccategory) throws PremiumException;

	Cod_premium checkPremium(String cbranch, String ccategory, String csection, String cguarantee, String cpremium) throws PremiumException;

	Cod_premium checkPremium(String cpremium) throws PremiumException;

	Cod_tax checkTax(String ctax) throws PremiumException;;
}
