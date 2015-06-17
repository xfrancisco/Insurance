package org.mfi.movements.person;

import java.math.BigDecimal;
import java.util.Date;

import org.mfi.movements.MovementCode;

public class ModQuoteMovement extends QuoteMovement {

	public ModQuoteMovement(Date acceptanceDate, String cbranch, String ccategory, String cduration, String cquotestatus, String cuseruw,
			Date endval, BigDecimal guaranteedAmount, long numclibroker, long numclileader, BigDecimal premiumAmount, Date receptionDate,
			BigDecimal sharepart, Date startVal, int numquote) {
		super(MovementCode.MODQUOTE, acceptanceDate, cbranch, ccategory, cduration, cquotestatus, cuseruw, endval, guaranteedAmount, numclibroker,
				numclileader, premiumAmount, receptionDate, sharepart, startVal, numquote);
	}

}
