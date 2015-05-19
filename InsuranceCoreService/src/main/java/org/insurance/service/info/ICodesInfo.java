package org.insurance.service.info;

import java.util.List;

import org.insurance.conf.Cod_version;

public interface ICodesInfo {

	<T> List<T> getCodeTableList(String queryString, boolean allValues);

	Cod_version getCurrentVersion();
}
