package org.insurance.service.check;

import java.math.BigDecimal;
import java.util.Date;

import org.insurance.exception.CommonException;

public interface ICommonCheck {

	void checkAmount(BigDecimal amount) throws CommonException;

	void checkPercentage(BigDecimal percentage) throws CommonException;

	void checkPeriod(Date start, Date end) throws CommonException;

}
