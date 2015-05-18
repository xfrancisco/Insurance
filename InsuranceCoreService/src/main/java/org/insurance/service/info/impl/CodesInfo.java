package org.insurance.service.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.ICodesInfo;
import org.springframework.stereotype.Service;

@Service
public class CodesInfo extends ServiceCore implements ICodesInfo{

	@Override
	public <T> List<T> getCodeTableList(String queryString, boolean allValues) {
		List<T> tmp = genericDao.getListByHQLQuery(queryString);
		List<T> result = new ArrayList<T>();
		if (!allValues) {
			for (T t : tmp) {
				try {
					String indvali = BeanUtils.getProperty(t, "indvali");
					if ("1".equals(indvali)) {
						result.add(t);
					}
				} catch (Exception e) {
					result.add(t);
				}
			}
			return result;
		}
		return tmp;
	}

}