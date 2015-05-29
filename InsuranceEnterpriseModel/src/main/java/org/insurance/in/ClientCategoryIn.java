package org.insurance.in;

import org.insurance.validation.constraints.Mandatory;

public class ClientCategoryIn {
	@Mandatory
	String categoryId;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

}
