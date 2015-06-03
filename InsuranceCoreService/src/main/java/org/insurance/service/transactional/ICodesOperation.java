package org.insurance.service.transactional;

import org.insurance.conf.Cod_table;
import org.insurance.exception.CodesException;

public interface ICodesOperation {

	void updateCodeTable(final Cod_table codTable, final String code, final String label, boolean isValid) throws CodesException;

	void insertCodeTable(final Cod_table codTable, final String code, final String label, boolean isValid) throws CodesException;

}
