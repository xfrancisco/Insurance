package org.mfi.service.transactional.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.mfi.conf.Cod_table;
import org.mfi.exception.CodesException;
import org.mfi.exception.CodesException.ErrorCode;
import org.mfi.service.ServiceCore;
import org.mfi.service.transactional.ICodesOperation;
import org.mfi.util.MappingUtils;
import org.springframework.stereotype.Service;

@Service
public class CodesOperation extends ServiceCore implements ICodesOperation {

	private final static String packagepath = "org.mfi.conf.";

	private Object instanciateCode(Cod_table codTable, String code, String label, boolean isValid) throws CodesException {
		Object result = null;
		try {
			result = Class.forName(packagepath + codTable.getTablename()).newInstance();
			BeanUtils.setProperty(result, codTable.getTablecode(), code);
			BeanUtils.setProperty(result, codTable.getTablelabel(), label);
			BeanUtils.setProperty(result, "indvali", MappingUtils.boolToString(isValid));
		} catch (Exception e) {
			throw new CodesException(ErrorCode.ERR_BIZ_CODES_INVALID_OBJECT, codTable.getTablename());
		}
		return result;
	}

	@Override
	public void updateCodeTable(Cod_table codTable, String code, String label, boolean isValid) throws CodesException {
		Object result = instanciateCode(codTable, code, label, isValid);
		genericDao.merge(result);

	}

	@Override
	public void insertCodeTable(Cod_table codTable, String code, String label, boolean isValid) throws CodesException {
		Object result = instanciateCode(codTable, code, label, isValid);
		genericDao.saveOrUpdate(result);

	}

}
