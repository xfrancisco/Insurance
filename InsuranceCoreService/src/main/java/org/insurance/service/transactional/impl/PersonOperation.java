package org.insurance.service.transactional.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.insurance.data.Cli_address;
import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;
import org.insurance.movements.person.DelCategoryMovement;
import org.insurance.movements.person.NewAddressMovement;
import org.insurance.movements.person.NewCategoryMovement;
import org.insurance.movements.person.NewPersonMovement;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IPersonInfo;
import org.insurance.service.transactional.IMovementOperation;
import org.insurance.service.transactional.IPersonOperation;
import org.springframework.stereotype.Service;

@Service
public class PersonOperation extends ServiceCore implements IPersonOperation {
	@Inject
	private IMovementOperation movementOperation;

	@Inject
	private IPersonInfo personInfo;

	@Override
	public Long insertClient(final String cuser, final Cli_client client) {
		client.setCusercre(cuser);
		client.setCreationDate(dbHelper.getNow());
		Long numcli = (Long) genericDao.save(client);
		NewPersonMovement movement = new NewPersonMovement(client.getCcivil(), client.getName(), client.getFirstname(), client.getCompanyname(),
				client.getCompanyid());
		movementOperation.insertMovement(numcli, null, cuser, movement);
		return numcli;
	}

	@Override
	public Long insertAddress(final String cuser, Cli_address address) {
		address.setCusercre(cuser);
		address.setCreationDate(dbHelper.getNow());
		Long numaddress = (Long) genericDao.save(address);
		NewAddressMovement movement = new NewAddressMovement(address.getStreet1(), address.getStreet2(), address.getStreet3(), address.getStreet4(),
				address.getCpostal(), address.getCity(), address.getCcountry());
		movementOperation.insertMovement(address.getNumcli(), null, cuser, movement);
		return numaddress;
	}

	@Override
	public Long updateClient(final String cuser, final Cli_client client) {
		client.setCusermod(cuser);
		client.setModifDate(dbHelper.getNow());
		genericDao.saveOrUpdate(client);
		return client.getNumcli();
	}

	@Override
	public Long updateAddress(final String cuser, Cli_address address) {
		address.setCusermod(cuser);
		address.setModifDate(dbHelper.getNow());
		genericDao.merge(address);
		return address.getNumaddress();
	}

	@Override
	public void insertCategories(String cuser, List<Cli_catcli> categories) {
		for (Cli_catcli cliCatcli : categories) {
			genericDao.save(cliCatcli);
			NewCategoryMovement movement = new NewCategoryMovement(cliCatcli.getCcatcli());
			movementOperation.insertMovement(cliCatcli.getNumcli(), null, cuser, movement);
		}

	}

	@Override
	public void updateCategories(long numcli, String cuser, List<Cli_catcli> categories) {
		List<Cli_catcli> oldCategories = personInfo.getCategories(numcli);

		List<Cli_catcli> removedCategories = new ArrayList<Cli_catcli>();
		List<Cli_catcli> addedCategories = new ArrayList<Cli_catcli>();
		for (Cli_catcli old : oldCategories) {
			if (!categories.contains(old))
				removedCategories.add(old);
		}

		for (Cli_catcli current : categories) {
			if (!oldCategories.contains(current))
				addedCategories.add(current);
		}

		for (Cli_catcli cliCatcli : addedCategories) {
			genericDao.save(cliCatcli);
			NewCategoryMovement movement = new NewCategoryMovement(cliCatcli.getCcatcli());
			movementOperation.insertMovement(cliCatcli.getNumcli(), null, cuser, movement);
		}

		for (Cli_catcli cliCatcli : removedCategories) {
			DelCategoryMovement movement = new DelCategoryMovement(cliCatcli.getCcatcli());
			movementOperation.insertMovement(cliCatcli.getNumcli(), null, cuser, movement);
			genericDao.delete(cliCatcli);
		}
	}

}
