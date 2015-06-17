package org.mfi.service.check.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.mfi.exception.CommonException;
import org.mfi.exception.CommonException.ErrorCode;
import org.mfi.service.ServiceCore;
import org.mfi.service.check.ICommonCheck;
import org.mfi.util.DateUtils;
import org.mfi.util.DateUtils.DatePattern;

public class CommonCheck extends ServiceCore implements ICommonCheck {

	private static BigDecimal min = new BigDecimal(0);
	private static BigDecimal maxPercentage = new BigDecimal(100);

	@Override
	public void checkAmount(final BigDecimal amount) throws CommonException {
		int comparisonResult = amount.compareTo(min);
		if (comparisonResult <= 0)
			throw new CommonException(ErrorCode.ERR_BIZ_COMMON_INVALID_AMOUNT, amount);
	}

	@Override
	public void checkPercentage(final BigDecimal percentage) throws CommonException {
		int comparisonResult = percentage.compareTo(maxPercentage);
		if (comparisonResult > 0)
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

	@Override
	public void checkShareOnPremium(final BigDecimal share, final String cpremium) throws CommonException {
		if (share.compareTo(maxPercentage) != 0)
			throw new CommonException(ErrorCode.ERR_BIZ_COMMON_INVALID_GLOBAL_SHARE, cpremium, share);

	}

}
