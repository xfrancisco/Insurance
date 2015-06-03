package org.insurance.service.info.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Cod_table;
import org.insurance.conf.Cod_version;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.ICodesInfo;
import org.springframework.stereotype.Service;

@Service
public class CodesInfo extends ServiceCore implements ICodesInfo {

	@Override
	public <T> List<T> getCodeTableList(final String queryString, final boolean allValues) {
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

	@Override
	public Cod_version getCurrentVersion() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_version.class);
		criteria.addOrder(Order.desc("versionDate"));
		return genericDao.getFirstByCriteria(criteria);
	}

	@Override
	public List<Cod_table> getCodeTables() {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_table.class);
		return genericDao.getByCriteria(criteria);
	}

	@Override
	public Cod_table getCodeTable(final String ctable) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Cod_table.class);
		criteria.add(Restrictions.eq("ctable", ctable));
		return genericDao.getFirstByCriteria(criteria);
	}

}
