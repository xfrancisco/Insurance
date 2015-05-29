package org.insurance.service.transactional;

import java.util.List;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_email;
import org.insurance.data.Cli_phone;

public interface IContactOperation {

	Long insertAddress(long numcli, String cuser, Cli_address address);

	Long updateAddress(long numcli, String cuser, Cli_address address);

	void insertPhones(long numcli, List<Cli_phone> phones, String cuser);

	void insertEmails(long numcli, List<Cli_email> emails, String cuser);

	void updatePhones(Long numcli, List<Cli_phone> phones, String cuser);

	void updateEmails(Long numcli, List<Cli_email> emails, String cuser);

}
