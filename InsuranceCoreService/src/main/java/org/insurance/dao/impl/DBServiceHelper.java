package org.insurance.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.insurance.dao.IDBServiceHelper;
import org.insurance.dao.PLSQLStatement;
import org.insurance.dao.SessionFactoryManager;
import org.insurance.exception.TechnicalException;
import org.insurance.exception.TechnicalException.ErrorCode;
import org.insurance.util.NonNullUtils;
import org.springframework.stereotype.Repository;

@Repository
public class DBServiceHelper implements IDBServiceHelper {
	@Inject
	protected SessionFactoryManager sessionFactoryManager;

	private <T> Connection connection() {
		try {
			final Connection result = sessionFactoryManager.session().doReturningWork(
				    new ReturningWork<java.sql.Connection>() {
				        public java.sql.Connection execute(Connection connection) throws SQLException 
				        { 
				        	final Connection result = connection.getMetaData().getConnection();
							if (result == null) {
								throw new TechnicalException(ErrorCode.ERR_TECH_SQLCONNECTION);
							}
							return result;
				        }
				    }
				);
			return result;
		} catch (Exception e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLCONNECTION, e);
		}
	}

	@Override
	public PLSQLStatement preparePLSQL(final String sqlStatement) {
		return new PLSQLStatement(connection(), sqlStatement);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> sqlToDtoList(final String sqlQuery, Class<T> dtoClass, Map<String, Object> sqlParams) {
		try {
			final Query query = sessionFactoryManager.session().createSQLQuery(sqlQuery);
			for (Map.Entry<String, Object> entry : sqlParams.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return NonNullUtils.nonNullList(query.setResultTransformer(Transformers.aliasToBean(dtoClass)).list());
		} catch (HibernateException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> sqlToDtoList(final String sqlQuery, Class<T> dtoClass, Map<String, Object> sqlParams, Map<String, Type> paramTypes)
			 {
		try {
			final Query query = sessionFactoryManager.session().createSQLQuery(sqlQuery);
			for (Map.Entry<String, Object> entry : sqlParams.entrySet()) {
				//			for (String paramName : sqlParams.keySet()) {
				Object paramValue = entry.getValue();
				if (paramValue != null) {
					query.setParameter(entry.getKey(), paramValue);
				} else {
					query.setParameter(entry.getKey(), null, paramTypes.get(entry.getKey()));
				}
			}

			return NonNullUtils.nonNullList(query.setResultTransformer(Transformers.aliasToBean(dtoClass)).list());
		} catch (HibernateException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> sqlToDtoWithScalarsList(final String sqlQuery, Class<T> dtoClass, Map<String, Object> sqlParams, Map<String, Type> scalarsMap)
			{
		try {
			//FIXME nullete à controler scalarsMap sqlParams
			SQLQuery query = sessionFactoryManager.session().createSQLQuery(sqlQuery);

			for (Map.Entry<String, Type> entry : scalarsMap.entrySet()) {
				//			Set<String> set = scalarsMap.keySet();
				//			for (String key : set) {
				query.addScalar(entry.getKey(), entry.getValue());
			}
			for (Map.Entry<String, Object> entry : sqlParams.entrySet()) {
				//			for (String paramName : sqlParams.keySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return NonNullUtils.nonNullList(query.setResultTransformer(Transformers.aliasToBean(dtoClass)).list());
		} catch (HibernateException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e);
		}
	}

	@Override
	public <T> T sqlToDto(final String sqlQuery, Class<T> dtoClass, Map<String, Object> sqlParams, Map<String, Type> scalars)
			 {
		final List<T> list = sqlToDtoWithScalarsList(sqlQuery, dtoClass, sqlParams, scalars);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	/*@Nullable*/
	public <T> T sqlToDto(final String sqlQuery, Class<T> dtoClass, Map<String, Object> sqlParams) {
		final List<T> list = sqlToDtoList(sqlQuery, dtoClass, sqlParams);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	/*@Nullable*/
	public <T> T sqlToDto(String sqlQuery, Class<T> dtoClass) {
		return sqlToDto(sqlQuery, dtoClass, (Map<String, Object>) Collections.EMPTY_MAP);
	}

	@SuppressWarnings({ "unchecked"})
	@Override
	public <T> List<T> sqlToDtoList(String sqlQuery, Class<T> dtoClass) {
		return sqlToDtoList(sqlQuery, dtoClass, Collections.EMPTY_MAP);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T simpleSelect(String sqlQuery) {
		try {
			final Query query = sessionFactoryManager.session().createSQLQuery(sqlQuery);
			final T result = (T) query.uniqueResult();
			if (result == null) {
				throw new TechnicalException(ErrorCode.ERR_TECH_NON_NULL_EXPECTED, sqlQuery);
			}
			return result;
		} catch (HibernateException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e, sqlQuery);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T simpleSelect(String sqlQuery, Map<String, Object> sqlParams) {
		try {
			final Query query = sessionFactoryManager.session().createSQLQuery(sqlQuery);
			for (Map.Entry<String, Object> entry : sqlParams.entrySet()) {
				//			for (String paramName : sqlParams.keySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}

			final T result = (T) query.uniqueResult();
			if (result == null) {
				throw new TechnicalException(ErrorCode.ERR_TECH_NON_NULL_EXPECTED, sqlQuery);
			}
			return result;
		} catch (HibernateException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e, sqlQuery);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> simpleSelectList(String sqlQuery) {
		try {
			final Query query = sessionFactoryManager.session().createSQLQuery(sqlQuery);
			return NonNullUtils.nonNullList(query.list());
		} catch (HibernateException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e, sqlQuery);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> simpleSelectList(String sqlQuery, Map<String, Object> sqlParams) {
		try {
			final Query query = sessionFactoryManager.session().createSQLQuery(sqlQuery);
			for (Map.Entry<String, Object> entry : sqlParams.entrySet()) {
				//			for (String paramName : sqlParams.keySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return NonNullUtils.nonNullList(query.list());
		} catch (HibernateException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e, sqlQuery);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	/*@Nullable*/
	public <T> T simpleSelectNullable(String sqlQuery) {
		try {
			final Query query = sessionFactoryManager.session().createSQLQuery(sqlQuery);
			return (T) query.uniqueResult();
		} catch (HibernateException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e, sqlQuery);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T simpleSelectNullable(String sqlQuery, Map<String, Object> sqlParams) {
		try {
			final Query query = sessionFactoryManager.session().createSQLQuery(sqlQuery);
			for (Map.Entry<String, Object> entry : sqlParams.entrySet()) {
				//			for (String paramName : sqlParams.keySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
			return (T) query.uniqueResult();
		} catch (HibernateException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e, sqlQuery);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T simpleSelect(String sqlQuery, Map<String, Object> sqlParams, Map<String, Type> paramTypes) {
		try {
			final Query query = sessionFactoryManager.session().createSQLQuery(sqlQuery);
			for (Map.Entry<String, Object> entry : sqlParams.entrySet()) {
				//			for (String paramName : sqlParams.keySet()) {
				Object paramValue = entry.getValue();
				if (paramValue != null) {
					query.setParameter(entry.getKey(), paramValue);
				} else {
					query.setParameter(entry.getKey(), null, paramTypes.get(entry.getKey()));
				}

			}

			final T result = (T) query.uniqueResult();
			if (result == null) {
				throw new TechnicalException(ErrorCode.ERR_TECH_NON_NULL_EXPECTED, sqlQuery);
			}
			return result;
		} catch (HibernateException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e, sqlQuery);
		}
	}
}

