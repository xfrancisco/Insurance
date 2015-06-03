package org.insurance.service.check;

import org.insurance.conf.Cod_table;
import org.insurance.exception.CodesException;

public interface ICodesCheck {

	Cod_table checkCodeTable(final String ctable) throws CodesException;
}
