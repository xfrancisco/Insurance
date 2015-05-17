package org.insurance.dao.impl;


import java.util.Date;

import javax.inject.Inject;

import org.insurance.dao.IDBHelper;
import org.insurance.dao.IDBServiceHelper;
import org.springframework.stereotype.Repository;

@Repository
public class DBHelper implements IDBHelper {

	@Inject
	private IDBServiceHelper dBServiceHelper;

	@Override
	public Date getNow() {
		return dBServiceHelper.simpleSelect("select sysdate from dual");
	}

	@Override
	public Date getToday() {
		return dBServiceHelper.simpleSelect("select trunc(sysdate) from dual");
	}

	@Override
	public long getSequenceNextVal(String sequenceName) {
		return Long.parseLong(dBServiceHelper.simpleSelect("select " + sequenceName + ".nextval from dual").toString());
	}
}

