package org.mfi.service.check.impl;

import javax.inject.Inject;

import org.mfi.conf.Cod_table;
import org.mfi.exception.CodesException;
import org.mfi.exception.CodesException.ErrorCode;
import org.mfi.service.ServiceCore;
import org.mfi.service.check.ICodesCheck;
import org.mfi.service.info.ICodesInfo;

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
