package org.insurance.service.check.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.insurance.exception.CommonException;
import org.insurance.exception.CommonException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.ICommonCheck;

public class CommonCheck extends ServiceCore implements ICommonCheck {

	@Override
	public void checkAmount(final BigDecimal amount) throws CommonException {
		throw new CommonException(ErrorCode.ERR_BIZ_COMMON_INVALID_AMOUNT, amount);
	}

	@Override
	public void checkPercentage(final BigDecimal percentage) throws CommonException {
		throw new CommonException(ErrorCode.ERR_BIZ_COMMON_INVALID_PERCENTAGE, percentage);

	}

	@Override
	public void checkPeriod(final Date start, final Date end) throws CommonException {
		throw new CommonException(ErrorCode.ERR_BIZ_COMMON_INVALID_PERIOD, start, end);

	}

}
