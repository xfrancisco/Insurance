package org.mfi.service.info;

import java.util.List;

import org.mfi.conf.Cod_branch;
import org.mfi.conf.Cod_category;
import org.mfi.conf.Cod_guarantee;
import org.mfi.conf.Cod_premium;
import org.mfi.conf.Cod_premiumconfig;
import org.mfi.conf.Cod_section;
import org.mfi.conf.Cod_tax;

public interface IPremiumInfo {

	List<Cod_premiumconfig> getPremiums();

	Cod_branch getBranch(String cbranch);

	Cod_category getCategory(String ccategory);

	Cod_section getSection(String csection);

	Cod_guarantee getGuarantee(String cguarantee);

	Cod_premium getPremium(String cpremium);

	List<Cod_branch> getBranches();

	public List<Cod_category> getCategories(final String cbranch);

	public List<Cod_section> getSections(String cbranch, String ccategory);

	public List<Cod_guarantee> getGuarantees(String cbranch, String ccategory, String csection);

	public List<Cod_premium> getPremiums(String cbranch, String ccategory, String csection, String cguarantee);

	Cod_category getCategory(String cbranch, String ccategory);

	Cod_premium getPremium(String cbranch, String ccategory, String csection, String cguarantee, String cpremium);

	Cod_tax getTax(String cpremium);

	Cod_tax getTaxByPremium(String cpremium);
}
