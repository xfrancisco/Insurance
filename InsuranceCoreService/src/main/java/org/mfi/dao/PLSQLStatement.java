package org.mfi.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.jdbc.OracleConnectionWrapper;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
//import org.eclipse.jdt.annotation.Nullable;
import org.mfi.exception.TechnicalException;
import org.mfi.exception.TechnicalException.ErrorCode;
import org.mfi.util.JSonUtils;
import org.mfi.util.MappingUtils;

public class PLSQLStatement {

	private static final Logger logger = Logger.getLogger(PLSQLStatement.class);

	private final CallableStatement cstmt;
	private final Connection connection;
	private int bindpos;
	private final Map<Integer, ResultInfo<?>> resultMap;
	private boolean executed = false;

	public PLSQLStatement(final Connection connection, final String sqlStatement) {
		this.bindpos = 1;
		this.connection = connection;
		this.resultMap = new HashMap<>();
		try {
			final CallableStatement preparedStmt = this.connection.prepareCall(sqlStatement);
			if (preparedStmt == null) {
				throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION);
			} else {
				cstmt = preparedStmt;
			}
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e);
		}
	}

	public PLSQLStatement bind(/*@Nullable*/String value) {
		try {
			if (StringUtils.isNotBlank(value)) {
				cstmt.setString(bindpos++, value);
			} else {
				cstmt.setNull(bindpos++, java.sql.Types.VARCHAR);
			}
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;
	}

	public PLSQLStatement bind(/*@Nullable*/Integer value) {
		try {
			if (null != value) {
				cstmt.setInt(bindpos++, value.intValue());
			} else {
				cstmt.setNull(bindpos++, java.sql.Types.NUMERIC);
			}
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;

	}

	public PLSQLStatement bind(/*@Nullable*/Long value) {
		try {
			if (null != value) {
				cstmt.setLong(bindpos++, value.longValue());
			} else {
				cstmt.setNull(bindpos++, java.sql.Types.NUMERIC);
			}
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;
	}

	public PLSQLStatement bind(/*@Nullable*/Double value) {
		try {
			if (null != value) {
				cstmt.setDouble(bindpos++, value.doubleValue());
			} else {
				cstmt.setNull(bindpos++, java.sql.Types.NUMERIC);
			}
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;
	}

	public PLSQLStatement bind(/*@Nullable*/Date value) {
		try {
			if (value != null) {
				cstmt.setDate(bindpos++, MappingUtils.toSqlDate(value));
			} else {
				cstmt.setNull(bindpos++, java.sql.Types.DATE);
			}
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;
	}

	public PLSQLStatement bindTimeStamp(/*@Nullable*/Date value) {
		try {
			if (value != null) {
				cstmt.setTimestamp(bindpos++, new java.sql.Timestamp(value.getTime()));
			} else {
				cstmt.setNull(bindpos++, java.sql.Types.TIMESTAMP);
			}
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;
	}

	public PLSQLStatement bind(/*@Nullable*/Boolean value) {
		try {
			if (value != null) {
				cstmt.setString(bindpos++, MappingUtils.boolToString(value.booleanValue()));
			} else {
				cstmt.setNull(bindpos++, java.sql.Types.VARCHAR);
			}
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;
	}

	public PLSQLStatement bind(boolean value) {
		try {
			cstmt.setString(bindpos++, MappingUtils.boolToString(value));
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;
	}

	public PLSQLStatement bind(int value) {
		try {
			cstmt.setInt(bindpos++, value);
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;
	}

	public PLSQLStatement bind(long value) {
		try {
			cstmt.setLong(bindpos++, value);
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;
	}

	public PLSQLStatement bind(/*@Nullable*/BigDecimal value) {
		try {
			if (value != null) {
				cstmt.setDouble(bindpos++, value.doubleValue());
			} else {
				cstmt.setNull(bindpos++, java.sql.Types.DOUBLE);
			}
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return this;
	}

	public PLSQLStatement bind(final Set<String> set) {
		return bind(new ArrayList<>(set), DBArrayType.TYPE_TABJAVA);
	}

	public PLSQLStatement bind(final List<String> list) {
		return bind(list, DBArrayType.TYPE_TABJAVA);
	}

	public PLSQLStatement bind(String[] array) {
		return bind(array, DBArrayType.TYPE_TABJAVA);
	}

	/**
	 * arraytype = DBArrayType.TYPE_TABMONTANT
	 * 
	 * @param array
	 * @return
	 * @throws TechnicalException
	 */
	public PLSQLStatement bind(BigDecimal[] array) {
		return bind(array, DBArrayType.TYPE_TABMONTANT);
	}

	public PLSQLStatement bind(final String[] array, final DBArrayType arrayType) {
		try {
			Connection delegateConnection = connection.unwrap(OracleConnectionWrapper.class);
			final ArrayDescriptor desc = ArrayDescriptor.createDescriptor(arrayType.getName(), delegateConnection);
			final ARRAY parameter = new ARRAY(desc, delegateConnection, array);

			try {
				cstmt.setArray(bindpos++, parameter);
			} catch (SQLException e) {
				close();
				throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, (Object) array);
			}

		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_INVALID_ORACLETYPE, e, arrayType.getName());
		}
		return this;
	}

	public PLSQLStatement bind(final BigDecimal[] array, final DBArrayType arrayType) {
		try {
			Connection delegateConnection = connection.unwrap(OracleConnectionWrapper.class);
			final ArrayDescriptor desc = ArrayDescriptor.createDescriptor(arrayType.getName(), delegateConnection);
			final ARRAY parameter = new ARRAY(desc, delegateConnection, array);

			try {
				cstmt.setArray(bindpos++, parameter);
			} catch (SQLException e) {
				close();
				throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, (Object) array);
			}

		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_INVALID_ORACLETYPE, e, arrayType.getName());
		}
		return this;
	}

	public PLSQLStatement bind(final List<String> list, final DBArrayType arrayType) {
		String[] test = new String[list.size()];
		list.toArray(test);
		return bind(test, arrayType);
	}

	public PLSQLStatement bindAsJson(final Object obj) {
		return bind(JSonUtils.objectToJson(obj, false));
	}

	public PLSQLStatement bindAsJson(final Object obj, final boolean includeNullField) {
		return bind(JSonUtils.objectToJson(obj, includeNullField));
	}

	public PLSQLStatement bindAsJson(final Object obj, final boolean includeNullField, final List<String> fieldToSerialize) {
		return bind(JSonUtils.objectToJson(obj, includeNullField, fieldToSerialize));
	}

	public ResultInfo<String> bindOutString() {
		return bindOut(Types.VARCHAR);
	}

	public ResultInfo<String> bindOutClob() {
		return bindOut(Types.CLOB);
	}

	public ResultInfo<Long> bindOutLong() {
		return bindOut(Types.NUMERIC);
	}

	public ResultInfo<BigDecimal> bindOutBigDecimal() {
		return bindOut(Types.DOUBLE);
	}

	public ResultInfo<Date> bindInOutDate(Date value) {
		try {
			if (value != null) {
				cstmt.setDate(bindpos, MappingUtils.toSqlDate(value));
			} else {
				cstmt.setNull(bindpos, java.sql.Types.DATE);
			}
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, value);
		}
		return bindOut(Types.DATE);
	}

	public ResultInfo<Date> bindOutDate() {
		return bindOut(Types.DATE);
	}

	public ResultInfo<java.sql.Timestamp> bindOutTimestamp() {
		return bindOut(Types.TIMESTAMP);
	}

	public ResultInfo<List<String>> bindOutArray() {
		return bindOut(OracleTypes.ARRAY, DBArrayType.TYPE_TABJAVA);
	}

	public ResultInfo<List<String>> bindOutArray(final DBArrayType arrayType) {
		return bindOut(OracleTypes.ARRAY, arrayType);
	}

	public ResultInfo<Long> bindOutNumeric() {
		return bindOut(Types.NUMERIC);
	}

	private <T> ResultInfo<T> bindOut(int sqltype) {
		return bindOut(sqltype, null);
	}

	private <T> ResultInfo<T> bindOut(int sqltype, final/*@Nullable*/DBArrayType arrayType) {
		ResultInfo<T> resultInfo = new ResultInfo<>(bindpos, sqltype);
		try {
			resultMap.put(bindpos, resultInfo);
			if (arrayType != null)
				cstmt.registerOutParameter(bindpos++, sqltype, arrayType.getName());
			else
				cstmt.registerOutParameter(bindpos++, sqltype);
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, "");
		}
		return resultInfo;
	}

	/**
	 * Utilisé en interne pour les bind in/out
	 * @param sqltype type sql en format oracle
	 * @param arrayType non null si tableau oracle (tabjava/tabcarticle)
	 * @return structure qui contiendra le resultat après execution
	 */
	private <T> ResultInfo<T> bindOutNoInc(int sqltype, final/*@Nullable*/DBArrayType arrayType) {
		ResultInfo<T> resultInfo = new ResultInfo<>(bindpos, sqltype);
		try {
			resultMap.put(bindpos, resultInfo);
			if (arrayType != null)
				cstmt.registerOutParameter(bindpos, sqltype, arrayType.getName());
			else
				cstmt.registerOutParameter(bindpos, sqltype);
		} catch (SQLException e) {
			close();
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLBINDEXCEPTION, e, "");
		}
		return resultInfo;
	}

	public ResultInfo<String> bindInOutString(final String value) {
		final ResultInfo<String> result = bindOutNoInc(Types.VARCHAR, null);
		bind(value);
		return result;
	}

	public ResultInfo<Long> bindInOutLong(final Long value) {
		final ResultInfo<Long> result = bindOutNoInc(Types.NUMERIC, null);
		bind(value);
		return result;
	}

	public ResultInfo<BigDecimal> bindInOutBigDecimal(final BigDecimal value) {
		final ResultInfo<BigDecimal> result = bindOutNoInc(Types.DOUBLE, null);
		bind(value);
		return result;
	}

	public ResultInfo<List<String>> bindInOutArray(final List<String> value) {
		final ResultInfo<List<String>> result = bindOutNoInc(OracleTypes.ARRAY, DBArrayType.TYPE_TABJAVA);
		bind(value);
		return result;
	}

	public ResultInfo<List<String>> bindInOutArray(final List<String> value, final DBArrayType arrayType) {
		final ResultInfo<List<String>> result = bindOutNoInc(OracleTypes.ARRAY, arrayType);
		bind(value, arrayType);
		return result;
	}

	public void execute() {
		try {
			cstmt.execute();
			for (int fieldPos : resultMap.keySet()) {
				ResultInfo<?> resultInfo = resultMap.get(fieldPos);
				resultInfo.fillValue();
			}
			executed = true;
		} catch (SQLException e) {
			throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e);
		} finally {
			close();
		}
	}

	protected void close() {
		try {
			cstmt.close();
		} catch (SQLException ex) {
			//Ne doit pas arriver mais n'empÃªche pas le dÃ©roulement du traitement
			//Donc pas de throw
			logger.error("Unable to close statement", ex);
		}
	}

	public class ResultInfo<T> {

		private int bindpos;
		private int sqltype;
		/*@Nullable*/
		protected T value;

		private ResultInfo(int bindpos, int sqltype) {
			this.bindpos = bindpos;
			this.sqltype = sqltype;
		}

		public/*@Nullable*/
		T getNullableValue() {
			if (!executed) {
				throw new TechnicalException(ErrorCode.ERR_TECH_STATEMENT_NOT_EXECUTED);
			}
			return value;
		}

		public T getValue() {
			if (!executed) {
				throw new TechnicalException(ErrorCode.ERR_TECH_STATEMENT_NOT_EXECUTED);
			}
			final T result = value;
			if (result == null) {
				throw new TechnicalException(ErrorCode.ERR_TECH_NON_NULL_EXPECTED);
			}
			return result;
		}

		private void setValue(/*@Nullable*/T value) {
			this.value = value;
		}

		@SuppressWarnings("unchecked")
		private void fillValue() {
			try {
				switch (sqltype) {
					case java.sql.Types.CLOB:
						Clob resultClob = cstmt.getClob(bindpos);
						setValue((T) resultClob.getSubString(1, (int) resultClob.length()));
						break;
					case java.sql.Types.VARCHAR:
						setValue((T) cstmt.getString(bindpos));
						break;
					case java.sql.Types.NUMERIC:
						setValue((T) ((Long) cstmt.getLong(bindpos)));
						break;
					case java.sql.Types.DOUBLE:
						setValue((T) (cstmt.getBigDecimal(bindpos)));
						break;
					case java.sql.Types.DATE:
						setValue((T) (cstmt.getDate(bindpos)));
						break;
					case java.sql.Types.TIMESTAMP:
						setValue((T) (cstmt.getTimestamp(bindpos)));
						break;
					case OracleTypes.ARRAY:
						String[] tabresult = (String[]) cstmt.getArray(1).getArray();
						final List<String> resValue = new ArrayList<String>(tabresult.length);
						for (int k = 0; k < tabresult.length; k++) {
							resValue.add(tabresult[k]);
						}
						setValue((T) resValue);
						break;
					default:
						throw new TechnicalException(ErrorCode.ERR_TECH_UNMAPPED_SQLTYPE, sqltype);
				}
			} catch (SQLException e) {
				throw new TechnicalException(ErrorCode.ERR_TECH_SQLEXCEPTION, e);
			}
		}
	}
}
