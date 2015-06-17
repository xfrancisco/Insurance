package org.mfi.service.transactional.impl;

import javax.inject.Inject;

import org.mfi.conf.Cod_quotestatus;
import org.mfi.data.Cli_quote;
import org.mfi.exception.QuoteAndContractException;
import org.mfi.exception.QuoteAndContractException.ErrorCode;
import org.mfi.movements.person.ModQuoteMovement;
import org.mfi.movements.person.NewQuoteMovement;
import org.mfi.movements.person.QuoteMovement;
import org.mfi.movements.person.ValidQuoteMovement;
import org.mfi.service.ServiceCore;
import org.mfi.service.info.IQuoteAndContractInfo;
import org.mfi.service.transactional.IMovementOperation;
import org.mfi.service.transactional.IQuoteOperation;
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
