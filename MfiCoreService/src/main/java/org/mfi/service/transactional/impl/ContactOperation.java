package org.mfi.service.transactional.impl;

import java.util.List;

import javax.inject.Inject;

import org.mfi.conf.Cod_postal;
import org.mfi.data.Cli_address;
import org.mfi.data.Cli_email;
import org.mfi.data.Cli_phone;
import org.mfi.movements.person.DelMailMovement;
import org.mfi.movements.person.DelPhoneMovement;
import org.mfi.movements.person.ModAddressMovement;
import org.mfi.movements.person.ModMailMovement;
import org.mfi.movements.person.ModPhoneMovement;
import org.mfi.movements.person.NewAddressMovement;
import org.mfi.movements.person.NewMailMovement;
import org.mfi.movements.person.NewPhoneMovement;
import org.mfi.service.ServiceCore;
import org.mfi.service.info.IContactInfo;
import org.mfi.service.transactional.IContactOperation;
import org.mfi.service.transactional.IMovementOperation;
import org.mfi.util.DateUtils;
import org.mfi.util.DateUtils.TimePeriod;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service
public class ContactOperation extends ServiceCore implements IContactOperation {
	@Inject
	private IMovementOperation movementOperation;

	@Inject
	private IContactInfo contactInfo;

	@Override
	public void insertAddresses(final long numcli, final String cuser, List<Cli_address> addresses) {
		for (Cli_address address : addresses) {
			insertAddress(numcli, cuser, address);
		}
	}

	private void insertAddress(final long numcli, final String cuser, Cli_address address) {
		address.setNumcli(numcli);
		address.setCusercre(cuser);
		address.setCreationDate(dbHelper.getNow());
		address.setStartval(DateUtils.convertUtilDateToSqlDate(dbHelper.getToday()));
		genericDao.save(address);
		NewAddressMovement movement = new NewAddressMovement(address.getCaddress(), address.getStreet1(), address.getStreet2(), address.getStreet3(),
				address.getStreet4(), address.getCpostal(), address.getCity(), address.getCcountry());
		movementOperation.insertMovement(address.getNumcli(), null, null, cuser, movement);
	}

	@Override
	public void updateAddresses(final long numcli, final String cuser, List<Cli_address> addresses) {
		for (Cli_address address : addresses) {
			Cli_address oldAddress = contactInfo.getAddressByType(numcli, address.getCaddress());
			if (!oldAddress.equals(address)) {
				oldAddress.setCusermod(cuser);
				oldAddress.setModifDate(dbHelper.getNow());
				oldAddress.setEndval(DateUtils.convertUtilDateToSqlDate(DateUtils.addToDate(dbHelper.getToday(), -1, TimePeriod.DAY)));
				genericDao.merge(oldAddress);

				ModAddressMovement movement = new ModAddressMovement(address.getCaddress(), address.getStreet1(), address.getStreet2(),
						address.getStreet3(), address.getStreet4(), address.getCpostal(), address.getCity(), address.getCcountry());
				movement.setOldValues(oldAddress.getCaddress(), oldAddress.getStreet1(), oldAddress.getStreet2(), oldAddress.getStreet3(),
						oldAddress.getStreet4(), oldAddress.getCpostal(), oldAddress.getCity(), oldAddress.getCcountry());

				address.setCreationDate(oldAddress.getCreationDate());
				address.setCusercre(oldAddress.getCusercre());
				address.setStartval(oldAddress.getStartval());
				address.setNumcli(numcli);
				address.setCusermod(cuser);
				address.setModifDate(dbHelper.getNow());
				genericDao.save(address);

				movementOperation.insertMovement(numcli, null, null, cuser, movement);
			}
		}

	}

	@Override
	public void insertPhones(final long numcli, List<Cli_phone> phones, final String cuser) {
		for (Cli_phone cliPhone : phones) {
			if (!Strings.isNullOrEmpty(cliPhone.getPhonenumber()))
				insertPhone(numcli, cliPhone, cuser);
		}
	}

	private void insertPhone(final long numcli, Cli_phone cliPhone, final String cuser) {
		cliPhone.setCreationDate(dbHelper.getNow());
		cliPhone.setCusercre(cuser);
		cliPhone.setStartval(DateUtils.convertUtilDateToSqlDate(dbHelper.getToday()));
		cliPhone.setNumcli(numcli);
		genericDao.save(cliPhone);
		NewPhoneMovement movement = new NewPhoneMovement(cliPhone.getCphone(), cliPhone.getPhonenumber());
		movementOperation.insertMovement(numcli, null, null, cuser, movement);
	}

	@Override
	public void insertEmails(final long numcli, List<Cli_email> emails, final String cuser) {
		for (Cli_email cliEmail : emails) {
			if (!Strings.isNullOrEmpty(cliEmail.getEmail()))
				insertEmail(numcli, cliEmail, cuser);
		}

	}

	private void insertEmail(final long numcli, Cli_email cliEmail, final String cuser) {
		cliEmail.setCreationDate(dbHelper.getNow());
		cliEmail.setCusercre(cuser);
		cliEmail.setStartval(DateUtils.convertUtilDateToSqlDate(dbHelper.getToday()));
		cliEmail.setNumcli(numcli);
		genericDao.save(cliEmail);
		NewMailMovement movement = new NewMailMovement(cliEmail.getCemail(), cliEmail.getEmail());
		movementOperation.insertMovement(numcli, null, null, cuser, movement);
	}

	private void deleteEmail(final long numcli, Cli_email cliEmail, final String cuser) {
		cliEmail.setNumcli(numcli);
		cliEmail.setModifDate(dbHelper.getNow());
		cliEmail.setCusermod(cuser);
		cliEmail.setEndval(DateUtils.convertUtilDateToSqlDate(DateUtils.addToDate(dbHelper.getToday(), -1, TimePeriod.DAY)));
		genericDao.merge(cliEmail);
		DelMailMovement movement = new DelMailMovement();
		movement.setOldValues(cliEmail.getCemail(), cliEmail.getEmail());
		movementOperation.insertMovement(numcli, null, null, cuser, movement);
	}

	private void deletePhone(final long numcli, Cli_phone cliPhone, final String cuser) {
		cliPhone.setNumcli(numcli);
		cliPhone.setModifDate(dbHelper.getNow());
		cliPhone.setCusermod(cuser);
		cliPhone.setEndval(DateUtils.convertUtilDateToSqlDate(DateUtils.addToDate(dbHelper.getToday(), -1, TimePeriod.DAY)));
		genericDao.merge(cliPhone);
		DelPhoneMovement movement = new DelPhoneMovement();
		movement.setOldValues(cliPhone.getCphone(), cliPhone.getPhonenumber());
		movementOperation.insertMovement(numcli, null, null, cuser, movement);
	}

	@Override
	public void updateEmails(final long numcli, List<Cli_email> emails, final String cuser) {
		for (Cli_email cliEmail : emails) {
			Cli_email oldEmail = contactInfo.getEmailByType(numcli, cliEmail.getCemail());
			if (oldEmail == null) {
				if (!Strings.isNullOrEmpty(cliEmail.getEmail())) {
					// Nouvel email
					insertEmail(numcli, cliEmail, cuser);
					continue;
				} else {
					continue;
				}
			}
			if (!oldEmail.equals(cliEmail) && Strings.isNullOrEmpty(cliEmail.getEmail())) {
				// Suppression d'email
				deleteEmail(numcli, oldEmail, cuser);
				continue;
			}
			if (!oldEmail.equals(cliEmail) && !Strings.isNullOrEmpty(cliEmail.getEmail())) {
				// Mise à jour d'email
				updateEmail(numcli, oldEmail, cliEmail, cuser);
				continue;
			}
		}
	}

	private void updateEmail(final long numcli, Cli_email oldEmail, Cli_email newMail, final String cuser) {
		oldEmail.setEndval(DateUtils.convertUtilDateToSqlDate(DateUtils.addToDate(dbHelper.getToday(), -1, TimePeriod.DAY)));
		oldEmail.setModifDate(dbHelper.getNow());
		oldEmail.setCusermod(cuser);
		genericDao.merge(oldEmail);

		newMail.setCusercre(oldEmail.getCusercre());
		newMail.setCreationDate(oldEmail.getCreationDate());
		newMail.setModifDate(dbHelper.getNow());
		newMail.setStartval(oldEmail.getStartval());
		newMail.setCusermod(cuser);
		newMail.setNumcli(numcli);
		genericDao.save(newMail);

		ModMailMovement movement = new ModMailMovement(newMail.getCemail(), newMail.getEmail());
		movement.setOldValues(oldEmail.getCemail(), oldEmail.getEmail());
		movementOperation.insertMovement(numcli, null, null, cuser, movement);
	}

	private void updatePhone(final long numcli, Cli_phone oldPhone, Cli_phone newPhone, final String cuser) {
		oldPhone.setEndval(DateUtils.convertUtilDateToSqlDate(DateUtils.addToDate(dbHelper.getToday(), -1, TimePeriod.DAY)));
		oldPhone.setModifDate(dbHelper.getNow());
		oldPhone.setCusermod(cuser);
		genericDao.merge(oldPhone);

		newPhone.setCusercre(oldPhone.getCusercre());
		newPhone.setCreationDate(oldPhone.getCreationDate());
		newPhone.setModifDate(dbHelper.getNow());
		newPhone.setCusermod(cuser);
		newPhone.setNumcli(numcli);
		newPhone.setStartval(oldPhone.getStartval());
		genericDao.save(newPhone);

		ModPhoneMovement movement = new ModPhoneMovement(newPhone.getCphone(), newPhone.getPhonenumber());
		movement.setOldValues(oldPhone.getCphone(), oldPhone.getPhonenumber());
		movementOperation.insertMovement(numcli, null, null, cuser, movement);
	}

	@Override
	public void updatePhones(final long numcli, List<Cli_phone> phones, final String cuser) {
		for (Cli_phone cliPhone : phones) {
			Cli_phone oldPhone = contactInfo.getPhoneByType(numcli, cliPhone.getCphone());
			if (oldPhone == null) {
				if (!Strings.isNullOrEmpty(cliPhone.getPhonenumber())) {
					// Nouvel téléphone
					insertPhone(numcli, cliPhone, cuser);
					continue;
				} else {
					continue;
				}
			}
			if (!oldPhone.equals(cliPhone) && Strings.isNullOrEmpty(cliPhone.getPhonenumber())) {
				// Suppression de téléphone
				deletePhone(numcli, oldPhone, cuser);
				continue;
			}
			if (!oldPhone.equals(cliPhone) && !Strings.isNullOrEmpty(cliPhone.getPhonenumber())) {
				// Mise à jour de téléphone
				updatePhone(numcli, oldPhone, cliPhone, cuser);
				continue;
			}
		}
	}

	@Override
	public void createZipCode(final Cod_postal codPostal) {
		genericDao.save(codPostal);
	}
}
