package org.insurance.service.check.impl;

import javax.inject.Inject;

import org.insurance.conf.Cod_civility;
import org.insurance.exception.PersonException;
import org.insurance.exception.PersonException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.info.IPersonInfo;

import com.google.common.base.Strings;

public class PersonCheck extends ServiceCore implements IPersonCheck {

	@Inject
	private IPersonInfo personInfo;

	@Override
	public void checkCivility(final String ccivil) throws PersonException {
		Cod_civility codCivility = personInfo.getCivility(ccivil);
		if (codCivility == null) {
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_CIVILITY, ccivil);
		}
	}

	@Override
	public void checkName(final String name, final String companyname, final String companyid) throws PersonException {
		if (Strings.isNullOrEmpty(name) && Strings.isNullOrEmpty(companyname))
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_MANDATORY_NAME_OR_COMPANYNAME);
		if (!Strings.isNullOrEmpty(companyname) && Strings.isNullOrEmpty(companyid))
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_MANDATORY_COMPANYID);
	}
}
