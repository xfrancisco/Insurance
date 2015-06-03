package org.insurance.service.transactional;

import java.util.List;

import org.insurance.conf.Cod_postal;
import org.insurance.data.Cli_address;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;

public interface IContactOperation {

	void insertAddresses(long numcli, String cuser, List<Cli_address> addresses);

	void updateAddresses(long numcli, String cuser, List<Cli_address> addresses);

	void insertPhones(long numcli, List<Cli_phone> phones, String cuser);

	void insertEmails(long numcli, List<Cli_email> emails, String cuser);

	void updatePhones(long numcli, List<Cli_phone> phones, String cuser);

	void updateEmails(long numcli, List<Cli_email> emails, String cuser);

	void createZipCode(Cod_postal codPostal);

}
