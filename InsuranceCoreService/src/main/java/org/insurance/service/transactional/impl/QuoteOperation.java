package org.insurance.service.transactional.impl;

import javax.inject.Inject;

import org.insurance.data.Cli_quote;
import org.insurance.movements.person.ModQuoteMovement;
import org.insurance.movements.person.NewQuoteMovement;
import org.insurance.movements.person.QuoteMovement;
import org.insurance.service.ServiceCore;
import org.insurance.service.transactional.IMovementOperation;
import org.insurance.service.transactional.IQuoteOperation;
import org.springframework.stereotype.Service;

@Service
public class QuoteOperation extends ServiceCore implements IQuoteOperation {

	@Inject
	private IMovementOperation movementOperation;

	@Override
	public void insertQuote(final long numcli, final int numquote, final String cuser, Cli_quote cliQuote) {
		cliQuote.setNumcli(numcli);
		cliQuote.setCusercre(cuser);
		cliQuote.setCreationDate(dbHelper.getNow());
		cliQuote.setNumquote(numquote);
		genericDao.save(cliQuote);

		movementOperation.insertMovement(numcli, null, cuser, populateQuoteMovement(cliQuote, true));
	}

	@Override
	public void updateQuote(final long numcli, final int numquote, final String cuser, Cli_quote cliQuote, Cli_quote oldQuote) {
		if (!cliQuote.equals(oldQuote)) {
			cliQuote.setCusercre(oldQuote.getCusercre());
			cliQuote.setCreationDate(oldQuote.getCreationDate());
			cliQuote.setNumcli(numcli);
			cliQuote.setNumquote(numquote);
			cliQuote.setCusermod(cuser);
			cliQuote.setModifDate(dbHelper.getNow());
			genericDao.merge(cliQuote);
			movementOperation.insertMovement(numcli, null, cuser, populateQuoteMovement(cliQuote, false));
		}
	}

	private QuoteMovement populateQuoteMovement(Cli_quote cliQuote, boolean isNew) {
		QuoteMovement movement = null;
		if (isNew)
			movement = new NewQuoteMovement(cliQuote.getAcceptancedate(), cliQuote.getCbranch(), cliQuote.getCcategory(), cliQuote.getCduration(),
					cliQuote.getCquotestatus(), cliQuote.getCuseruw(), cliQuote.getEndval(), cliQuote.getGuaranteedamount(),
					cliQuote.getNumclibroker(), cliQuote.getNumclileader(), cliQuote.getPremiumamount(), cliQuote.getReceptiondate(),
					cliQuote.getSharepart(), cliQuote.getStartval(), cliQuote.getNumquote());
		else
			movement = new ModQuoteMovement(cliQuote.getAcceptancedate(), cliQuote.getCbranch(), cliQuote.getCcategory(), cliQuote.getCduration(),
					cliQuote.getCquotestatus(), cliQuote.getCuseruw(), cliQuote.getEndval(), cliQuote.getGuaranteedamount(),
					cliQuote.getNumclibroker(), cliQuote.getNumclileader(), cliQuote.getPremiumamount(), cliQuote.getReceptiondate(),
					cliQuote.getSharepart(), cliQuote.getStartval(), cliQuote.getNumquote());
		return movement;

	}

}
