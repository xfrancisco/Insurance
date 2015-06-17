package org.mfi.service.check;

import org.mfi.conf.Cod_table;
import org.mfi.exception.CodesException;

public interface ICodesCheck {

	Cod_table checkCodeTable(final String ctable) throws CodesException;
}
