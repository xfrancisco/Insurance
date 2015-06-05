package org.insurance.service.transactional.impl;

import javax.inject.Inject;

import org.insurance.conf.Cod_quotestatus;
import org.insurance.data.Cli_quote;
import org.insurance.exception.QuoteAndContractException;
import org.insurance.exception.QuoteAndContractException.ErrorCode;
import org.insurance.movements.person.ModQuoteMovement;
import org.insurance.movements.person.NewQuoteMovement;
import org.insurance.movements.person.QuoteMovement;
import org.insurance.movements.person.ValidQuoteMovement;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IQuoteAndContractInfo;
import org.insurance.service.transactional.IMovementOperation;
import org.insurance.service.transactional.IQuoteOperation;
import org.springframework.stereotype.Service;

@Service
public class QuoteOperation extends ServiceCore implements IQuoteOperation {

	@Inject
	private IMovementOperation movementOperation;

	@Inject
	private IQuoteAndContractInfo quoteAndContractInfo;

	@Override
	public void insertQuote(final long numcli, final int numquote, final String cuser, Cli_quote cliQuote) {
		cliQuote.setNumcli(numcli);
		cliQuote.setCusercre(cuser);
		cliQuote.setCreationDate(dbHelper.getNow());
		cliQuote.setNumquote(numquote);
		genericDao.save(cliQuote);

		movementOperation.insertMovement(numcli, null, numquote, cuser, populateQuoteMovement(cliQuote, true));
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
			movementOperation.insertMovement(numcli, null, numquote, cuser, populateQuoteMovement(cliQuote, false));
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

	@Override
	public void validateQuote(String cuser, long numcli, int numquote, int numcon) throws QuoteAndContractException {
		Cod_quotestatus validatedStatus = quoteAndContractInfo.getValidatedQuoteStatus();
		if (validatedStatus == null)
			throw new QuoteAndContractException(ErrorCode.ERR_BIZ_QUOTECONTRACT_UNKNOWN_VALIDATED_STATUS);
		Cli_quote quote = quoteAndContractInfo.getQuote(numcli, numquote);
		quote.setCusermod(cuser);
		quote.setModifDate(dbHelper.getNow());
		quote.setNumcon(numcon);
		quote.setCquotestatus(validatedStatus.getCquotestatus());

		ValidQuoteMovement movement = new ValidQuoteMovement();
		movementOperation.insertMovement(numcli, null, numquote, cuser, movement);

	}

}
