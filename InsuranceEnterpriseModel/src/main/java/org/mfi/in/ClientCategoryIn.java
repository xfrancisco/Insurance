package org.mfi.in;

import org.mfi.validation.constraints.Mandatory;

import com.google.common.base.Strings;

public class ClientCategoryIn {
	@Mandatory
	String categoryId;

	public String getCategoryId() {
		return Strings.nullToEmpty(categoryId).toUpperCase().trim();
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

}
