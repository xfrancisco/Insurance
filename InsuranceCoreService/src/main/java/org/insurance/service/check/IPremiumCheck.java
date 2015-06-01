package org.insurance.service.check;

import org.insurance.conf.Cod_branch;
import org.insurance.conf.Cod_category;
import org.insurance.exception.PremiumException;

public interface IPremiumCheck {

	Cod_branch checkBranch(String cbranch) throws PremiumException;

	Cod_category checkCategory(String cbranch, String ccategory) throws PremiumException;

}
