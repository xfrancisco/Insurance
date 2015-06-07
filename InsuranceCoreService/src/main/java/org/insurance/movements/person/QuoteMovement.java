package org.insurance.movements.person;

import java.math.BigDecimal;
import java.util.Date;

import org.insurance.movements.Movement;
import org.insurance.movements.MovementCode;
import org.insurance.util.DateUtils;
import org.insurance.util.DateUtils.DatePattern;
import org.insurance.util.MappingUtils;

public class QuoteMovement extends Movement {

	protected QuoteMovement(MovementCode movement, final Date acceptanceDate, final String cbranch, final String ccategory, final String cduration,
			final String cquotestatus, final String cuseruw, final Date endval, final BigDecimal guaranteedAmount, final long numclibroker,
			final long numclileader, final BigDecimal premiumAmount, final Date receptionDate, final BigDecimal sharepart, final Date startVal,
			int numquote) {
		super(movement);
		addParameters(Fields.ACCEPTANCEDATE, DateUtils.formatDate(acceptanceDate, DatePattern.DATE_DD_MM_YYYY));
		addParameters(Fields.CBRANCH, cbranch);
		addParameters(Fields.CDURATION, cduration);
		addParameters(Fields.CQUOTESTATUS, cquotestatus);
		addParameters(Fields.CUSERUW, cuseruw);
		addParameters(Fields.ENDVAL, DateUtils.formatDate(endval, DatePattern.DATE_DD_MM_YYYY));
		addParameters(Fields.GUARANTEEDAMOUNT, MappingUtils.toString(guaranteedAmount));
		addParameters(Fields.NUMCLIBROKER, String.valueOf(numclibroker));
		addParameters(Fields.NUMCLILEADER, String.valueOf(numclileader));
		addParameters(Fields.PREMIUMAMOUNT, MappingUtils.toString(premiumAmount));
		addParameters(Fields.RECEPTIONDATE, DateUtils.formatDate(receptionDate, DatePattern.DATE_DD_MM_YYYY));
		addParameters(Fields.SHAREPART, MappingUtils.toString(sharepart));
		addParameters(Fields.STARTVAL, DateUtils.formatDate(startVal, DatePattern.DATE_DD_MM_YYYY));
		addParameters(Fields.NUMQUOTE, String.valueOf(numquote));

	}

	protected static enum Fields {
		ACCEPTANCEDATE,
		CBRANCH,
		CCATEGORY,
		CDURATION,
		CQUOTESTATUS,
		CUSERUW,
		ENDVAL,
		GUARANTEEDAMOUNT,
		NUMCLIBROKER,
		NUMCLILEADER,
		NUMQUOTE,
		PREMIUMAMOUNT,
		RECEPTIONDATE,
		SHAREPART,
		STARTVAL;
	}

}
