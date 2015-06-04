package org.insurance.service.info;

import java.util.List;

import org.insurance.conf.Cod_branch;
import org.insurance.conf.Cod_category;
import org.insurance.conf.Cod_guarantee;
import org.insurance.conf.Cod_premium;
import org.insurance.conf.Cod_premiumconfig;
import org.insurance.conf.Cod_section;

public interface IPremiumInfo {

	List<Cod_premiumconfig> getPremiums();

	Cod_branch getBranch(String cbranch);

	Cod_category getCategory(String ccategory);

	Cod_section getSection(String csection);

	Cod_guarantee getGuarantee(String cguarantee);

	Cod_premium getPremium(String cpremium);

	List<Cod_branch> getBranches();

	public List<Cod_category> getCategories(final String cbranch);

	public List<Cod_section> getSections(final String ccategory);

	public List<Cod_guarantee> getGuarantees(final String csection);

	public List<Cod_premium> getPremiums(final String cpremium);

	Cod_category getCategory(String cbranch, String ccategory);

	Cod_premium getPremium(String cbranch, String ccategory, String csection, String cguarantee, String cpremium);
}
