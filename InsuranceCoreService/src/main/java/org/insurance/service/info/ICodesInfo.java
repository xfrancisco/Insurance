package org.insurance.service.info;

import java.util.List;

public interface ICodesInfo {
	
	<T> List<T> getCodeTableList(String queryString, boolean allValues);
}
