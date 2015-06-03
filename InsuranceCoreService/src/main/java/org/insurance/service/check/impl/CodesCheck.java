package org.insurance.service.check.impl;

import javax.inject.Inject;

import org.insurance.conf.Cod_table;
import org.insurance.exception.CodesException;
import org.insurance.exception.CodesException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.check.ICodesCheck;
import org.insurance.service.info.ICodesInfo;

public class CodesCheck extends ServiceCore implements ICodesCheck {

	@Inject
	private ICodesInfo codesInfo;

	@Override
	public Cod_table checkCodeTable(final String ctable) throws CodesException {
		Cod_table codTable = codesInfo.getCodeTable(ctable);
		if (codTable == null)
			throw new CodesException(ErrorCode.ERR_BIZ_CODES_UNKNOWN_TABLE, ctable);
		return codTable;
	}
}
