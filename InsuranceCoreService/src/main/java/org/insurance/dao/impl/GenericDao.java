package org.insurance.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.ResultTransformer;
import org.insurance.dao.Condition;
import org.insurance.dao.IGenericDao;
import org.insurance.dao.SessionFactoryManager;
import org.insurance.exception.TechnicalException;
import org.insurance.util.NonNullUtils;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;

@Repository
public class GenericDao implements IGenericDao {

	@Inject
	protected SessionFactoryManager sessionFactoryManager;

	@Override
	public Session getSession() {
		final Session result = sessionFactoryManager.session();
		if (result == null)
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_NOSESSION);
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	/*@Nullable*/
	public <T> T getForUpdate(Class<T> type, Serializable id) {
		try {
			T tRet = null;
			if (id != null) {
				final Session session = this.getSession();
				tRet = (T) session.get(type, id);
				if (tRet != null) {
					this.getSession().refresh(tRet);
				}
			}
			return tRet;
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, type, id);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	/*@Nullable*/
	public <T> T get(Class<T> type, Serializable id) {
		try {
			T tRet = null;
			if (id != null) {
				final Session session = getSession();
				tRet = (T) session.get(type, id);
				session.evict(tRet);
			}
			return tRet;
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, type, id);
		}
	}

	@Override
	/*@Nullable*/
	public <T> T get(Class<T> type, Serializable id, boolean reload) {
		try {
			T t = get(type, id);
			if (reload && t != null) {
				this.getSession().refresh(t);
			}
			return t;
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, type, id, reload);
		}
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <T> List<T> getList(Class<T> type) {
		try {
			final Criteria criteriaQuery = getSession().createCriteria(type);
			return NonNullUtils.nonNullList(criteriaQuery.list());

		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, type);
		}
	}

	@Override
	public void saveOrUpdate(Object objectToSaveOrUpdate) {
		try {
			this.getSession().saveOrUpdate(objectToSaveOrUpdate);
			this.getSession().flush();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_WRITE, e);
		}
	}

	@Override
	public Object save(Object objectToSave) {
		Object valueToReturn = null;
		try {
			valueToReturn = this.getSession().save(objectToSave);
			this.getSession().flush();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_WRITE, e);
		}
		return valueToReturn;
	}

	@Override
	public Object merge(Object objectToMerge) {
		Object updatedValue = null;
		try {
			updatedValue = this.getSession().merge(objectToMerge);
			this.getSession().flush();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_WRITE, e);
		}
		return updatedValue;
	}

	@Override
	public void delete(Object objectToDelete) {
		try {
			this.getSession().delete(objectToDelete);
			this.getSession().flush();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_WRITE, e);
		}
	}

	@Override
	public <T> List<T> getByCriteria(Class<T> type, Condition condition) {
		return getByCriteriaWhithOrder(type, condition, null);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getByCriteriaWhithOrder(Class<T> type, Condition condition, Order order) {
		try {
			final Criteria criteriaQuery = getSession().createCriteria(type);

			if (condition != null) {
				for (Criterion criterion : condition) {
					criteriaQuery.add(criterion);
				}
			}
			if (order != null) {
				criteriaQuery.addOrder(order);
			}
			return NonNullUtils.nonNullList(criteriaQuery.list());

		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, type, condition);
		}
	}

	@Override
	/*@Nullable*/
	public <T> T getFirstByCriteria(Class<T> type, Condition condition) {
		final List<T> results = getByCriteria(type, condition);
		if (results != null && !results.isEmpty()) {
			return results.iterator().next();
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getByCriteria(DetachedCriteria criteria) {
		try {
			final Criteria resultCriteria = criteria.getExecutableCriteria(getSession());

			return NonNullUtils.nonNullList(resultCriteria.list());

		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, criteria);
		}
	}

	@Override
	/*@Nullable*/
	public <T> T getFirstByCriteria(DetachedCriteria criteria) {
		final List<T> result = getByCriteria(criteria);
		if (!result.isEmpty()) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getByNamedQuery(final String namedQuery, final Object... params) {
		try {
			Query query = getSession().getNamedQuery(namedQuery);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			return NonNullUtils.nonNullList(query.list());

		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_NAMEDQUERY, e, namedQuery, params);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getByNamedHQLQuery(final String namedQuery, final Map<String, Object> hqlParams) {
		try {
			Query query = getSession().getNamedQuery(namedQuery);
			for (Map.Entry<String, Object> entry : hqlParams.entrySet()) {
				//			for (String paramName : hqlParams.keySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return query.list();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_NAMEDQUERY, e, namedQuery, hqlParams);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getByQuery(final Class<T> clazz, final String whereCondition, final Map<String, Object> paramsKeyAndValue) {
		List<T> codes;
		StringBuilder stringBuilder = new StringBuilder("from ");
		stringBuilder.append(clazz.getName());

		try {
			if (!Strings.isNullOrEmpty(whereCondition)) {
				stringBuilder.append(" where ");
				stringBuilder.append(whereCondition);
			}
			Query query = this.getSession().createQuery(stringBuilder.toString());
			if (paramsKeyAndValue != null) {
				query.setProperties(paramsKeyAndValue);
			}

			codes = query.list();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, stringBuilder.toString());
		}

		return codes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getSingleByQuery(final Class<T> clazz, final String whereCondition, final Map<String, Object> paramsKeyAndValue) {
		T codes = null;
		StringBuilder stringBuilder = new StringBuilder("from ");
		stringBuilder.append(clazz.getName());

		try {
			if (!Strings.isNullOrEmpty(whereCondition)) {
				stringBuilder.append(" where ");
				stringBuilder.append(whereCondition);
			}
			Query query = this.getSession().createQuery(stringBuilder.toString());
			if (paramsKeyAndValue != null) {
				query.setProperties(paramsKeyAndValue);
			}

			codes = (T) query.uniqueResult();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, stringBuilder.toString());
		}

		return codes;
	}

	//==============================
	//les requetes avec pagination =
	//==============================
	@Override
	@SuppressWarnings({ "unchecked" })
	public <T> List<T> getPaginatedList(Class<T> type, int firstResult, int maxResultLimit) {
		try {
			final Criteria criteriaQuery = getSession().createCriteria(type);

			if (maxResultLimit > 0) {
				criteriaQuery.setMaxResults(maxResultLimit);
			}
			if (firstResult >= 0) {
				criteriaQuery.setFirstResult(firstResult);
			}

			return NonNullUtils.nonNullList(criteriaQuery.list());

		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, type);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getPaginatedListByCriteria(Class<T> type, Condition condition, int firstResult, int maxResultLimit) {
		try {
			final Criteria criteriaQuery = getSession().createCriteria(type);

			if (condition != null) {
				for (Criterion criterion : condition) {
					criteriaQuery.add(criterion);
				}
			}
			if (firstResult >= 0) {
				criteriaQuery.setFirstResult(firstResult);
			}

			if (maxResultLimit > 0) {
				criteriaQuery.setMaxResults(maxResultLimit);
			}
			return NonNullUtils.nonNullList(criteriaQuery.list());

		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, type, condition);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getPaginatedListByDetachedCriteria(DetachedCriteria criteria, int firstResult, int maxResultLimit) {
		try {
			final Criteria resultCriteria = criteria.getExecutableCriteria(getSession());
			if (firstResult >= 0) {
				resultCriteria.setFirstResult(firstResult);
			}
			if (firstResult >= 0) {
				resultCriteria.setFirstResult(firstResult);
			}
			if (maxResultLimit > 0) {
				resultCriteria.setMaxResults(maxResultLimit);
			}

			return NonNullUtils.nonNullList(resultCriteria.list());

		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, criteria);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getPaginatedListByNamedQuery(final String namedQuery, int firstResult, int maxResultLimit, final Object... params) {
		try {
			Query query = getSession().getNamedQuery(namedQuery);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			if (firstResult >= 0) {
				query.setFirstResult(firstResult);
			}
			if (maxResultLimit > 0) {
				query.setMaxResults(maxResultLimit);
			}
			return NonNullUtils.nonNullList(query.list());

		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_NAMEDQUERY, e, namedQuery, params);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getByHQLQuery(final String hqlQuery) {

		try {
			Query query = this.getSession().createQuery(hqlQuery);

			return (T) query.uniqueResult();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, hqlQuery);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getByHQLQuery(final String hqlQuery, final Object... params) {

		try {
			Query query = this.getSession().createQuery(hqlQuery);
			int i = 0;
			for (Object param : params) {
				query.setParameter(i++, param);
			}

			return (T) query.uniqueResult();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, hqlQuery);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByHQLQuery(final String hqlQuery, final Object... params) {

		try {
			Query query = this.getSession().createQuery(hqlQuery);
			int i = 0;
			for (Object param : params) {
				query.setParameter(i, param);
				i++;
			}

			return query.list();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, hqlQuery);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByHQLQuery(final String hqlQuery, final ResultTransformer resultTransformer, final Object... params) {

		try {
			Query query = this.getSession().createQuery(hqlQuery);
			int i = 0;
			for (Object param : params) {
				query.setParameter(i++, param);
			}
			query.setResultTransformer(resultTransformer);
			return query.list();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, hqlQuery);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getListByHQLQuery(final String hqlQuery, final Map<String, Object> hqlParams) {

		try {
			Query query = this.getSession().createQuery(hqlQuery);
			for (Map.Entry<String, Object> entry : hqlParams.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}

			return query.list();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, hqlQuery);
		}

	}

	@Override
	public void executeUpdateByHQLQuery(final String hqlQuery, final Object... params) {
		try {
			Query query = this.getSession().createQuery(hqlQuery);
			int i = 0;
			for (Object param : params) {
				query.setParameter(i++, param);
			}

			query.executeUpdate();
		} catch (HibernateException e) {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_HIBERNATE_READ, e, hqlQuery);
		}
	}

}
