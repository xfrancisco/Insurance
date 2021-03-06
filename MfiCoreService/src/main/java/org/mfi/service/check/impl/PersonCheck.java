package org.mfi.service.check.impl;

import java.util.List;

import javax.inject.Inject;

import org.mfi.conf.Cod_catcli;
import org.mfi.conf.Cod_civility;
import org.mfi.data.Cli_catcli;
import org.mfi.data.Cli_client;
import org.mfi.exception.PersonException;
import org.mfi.exception.PersonException.ErrorCode;
import org.mfi.service.ServiceCore;
import org.mfi.service.check.IPersonCheck;
import org.mfi.service.info.IPersonInfo;

import com.google.common.base.Strings;

public class PersonCheck extends ServiceCore implements IPersonCheck {

	@Inject
	private IPersonInfo personInfo;

	@Override
	public Cod_civility checkCivility(final String ccivil) throws PersonException {
		Cod_civility codCivility = personInfo.getCivility(ccivil);
		if (codCivility == null) {
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_CIVILITY, ccivil);
		}
		return codCivility;
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
	public Cod_catcli checkCategory(final String ccatcli) throws PersonException {
		Cod_catcli codCatcli = personInfo.getCategory(ccatcli);
		if (codCatcli == null) {
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_CATEGORY, ccatcli);
		}
		return codCatcli;
	}

	@Override
	public Cli_client checkPerson(final long numcli) throws PersonException {
		Cli_client client = personInfo.getPerson(numcli);
		if (client == null)
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_PERSON, numcli);
		return client;
	}

	@Override
	public void checkCategories(final List<Cli_catcli> categories) throws PersonException {
		for (Cli_catcli cliCatcli : categories) {
			checkCategory(cliCatcli.getCcatcli());
		}
	}

	@Override
	public Cli_client checkBroker(final long numclibroker) throws PersonException {
		Cli_client broker = personInfo.getBroker(numclibroker);
		if (broker == null)
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_BROKER, numclibroker);
		return broker;
	}

	@Override
	public Cli_client checkInsurer(final long numclileader) throws PersonException {
		Cli_client leader = personInfo.getInsurer(numclileader);
		if (leader == null)
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_LEADER, numclileader);
		return leader;

	}

	@Override
	public Cli_client checkClient(final long numcli) throws PersonException {
		Cli_client client = personInfo.getClient(numcli);
		if (client == null)
			throw new PersonException(ErrorCode.ERR_BIZ_PERSON_UNKNOWN_CLIENT, numcli);
		return client;

	}
}
