package org.insurance.service.check.impl;

import java.util.List;

import javax.inject.Inject;

import org.insurance.conf.Cod_catcli;
import org.insurance.conf.Cod_civility;
import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;
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
		// XFR : A la demande de LCO on retire ce contrôle
		/*if (!Strings.isNullOrEmpty(companyname) && Strings.isNullOrEmpty(companyid))
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_MANDATORY_COMPANYID);*/
	}

	@Override
	public void checkCategory(String ccatcli) throws PersonException {
		Cod_catcli codCatcli = personInfo.getCategory(ccatcli);
		if (codCatcli == null) {
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_CATEGORY, ccatcli);
		}
	}

	@Override
	public Cli_client checkAndGetPerson(long numcli) throws PersonException {
		Cli_client client = personInfo.getPerson(numcli);
		if (client == null)
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_PERSON, numcli);
		return client;
	}

	@Override
	public void checkCategories(List<Cli_catcli> categories) throws PersonException {
		for (Cli_catcli cliCatcli : categories) {
			checkCategory(cliCatcli.getCcatcli());
		}
	}

	@Override
	public Cli_client checkBroker(long numclibroker) throws PersonException {
		Cli_client broker = personInfo.getBroker(numclibroker);
		if (broker == null)
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_BROKER, numclibroker);
		return broker;
	}

	@Override
	public Cli_client checkLeader(long numclileader) throws PersonException {
		throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_LEADER, numclileader);

	}
}
