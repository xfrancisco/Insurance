package org.insurance.service.transactional.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.insurance.conf.Cod_table;
import org.insurance.exception.CodesException;
import org.insurance.exception.CodesException.ErrorCode;
import org.insurance.service.ServiceCore;
import org.insurance.service.transactional.ICodesOperation;
import org.insurance.util.MappingUtils;
import org.springframework.stereotype.Service;

@Service
public class CodesOperation extends ServiceCore implements ICodesOperation {

	private final static String packagepath = "org.insurance.conf.";

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
