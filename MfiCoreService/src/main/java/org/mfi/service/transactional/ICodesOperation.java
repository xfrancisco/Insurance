package org.mfi.service.transactional;

import org.mfi.conf.Cod_table;
import org.mfi.exception.CodesException;

public interface ICodesOperation {

	void updateCodeTable(final Cod_table codTable, final String code, final String label, boolean isValid) throws CodesException;

	void insertCodeTable(final Cod_table codTable, final String code, final String label, boolean isValid) throws CodesException;

}
