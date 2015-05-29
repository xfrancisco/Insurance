package org.insurance.service.transactional.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;
import org.insurance.movements.person.DelCategoryMovement;
import org.insurance.movements.person.ModPersonMovement;
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
	public Long updateClient(final long numcli, final String cuser, final Cli_client client) {
		Long nummouvmt = null;
		Cli_client oldClient = personInfo.getPerson(numcli);
		if (!oldClient.equals(client)) {
			client.setCusermod(cuser);
			client.setModifDate(dbHelper.getNow());
			ModPersonMovement movement = new ModPersonMovement(client.getCcivil(), client.getName(), client.getFirstname(), client.getCompanyname(),
					client.getCompanyid());
			movement.setOldValues(oldClient.getCcivil(), oldClient.getName(), oldClient.getFirstname(), oldClient.getCompanyname(),
					oldClient.getCompanyid());
			nummouvmt = movementOperation.insertMovement(numcli, 0, cuser, movement);
			genericDao.saveOrUpdate(client);
		}
		return nummouvmt;
	}

	@Override
	public void insertCategories(long numcli, String cuser, List<Cli_catcli> categories) {
		for (Cli_catcli cliCatcli : categories) {
			cliCatcli.setNumcli(numcli);
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
