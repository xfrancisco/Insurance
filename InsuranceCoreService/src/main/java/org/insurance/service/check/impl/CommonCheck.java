package org.insurance.service.check.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.insurance.exception.CommonException;
import org.insurance.exception.CommonException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.ICommonCheck;
import org.insurance.util.DateUtils;
import org.insurance.util.DateUtils.DatePattern;

public class CommonCheck extends ServiceCore implements ICommonCheck {

	private static BigDecimal min = new BigDecimal(0);
	private static BigDecimal maxPourcentage = new BigDecimal(100);

	@Override
	public void checkAmount(final BigDecimal amount) throws CommonException {
		int comparisonResult = amount.compareTo(min);
		if (comparisonResult <= 0)
			throw new CommonException(ErrorCode.ERR_BIZ_COMMON_INVALID_AMOUNT, amount);
	}

	@Override
	public void checkPercentage(final BigDecimal percentage) throws CommonException {
		int comparisonResult = percentage.compareTo(maxPourcentage);
		if (comparisonResult >= 0)
			throw new CommonException(ErrorCode.ERR_BIZ_COMMON_INVALID_PERCENTAGE, percentage);

	}

	@Override
	public void checkPeriod(final Date start, final Date end) throws CommonException {
		if (end != null && start != null) {
			if (start.after(end))
				throw new CommonException(ErrorCode.ERR_BIZ_COMMON_INVALID_PERIOD, DateUtils.formatDate(start, DatePattern.DATE_DD_MM_YYYY),
						DateUtils.formatDate(end, DatePattern.DATE_DD_MM_YYYY));
		}
	}

}
