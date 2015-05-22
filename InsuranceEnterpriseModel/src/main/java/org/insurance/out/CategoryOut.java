package org.insurance.out;

import java.io.Serializable;

public class CategoryOut extends EntityOut implements Serializable {
	private static final long serialVersionUID = 323822681239888128L;

	private String categoryId;
	private String categoryLabel;
	private boolean isValid = false;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryLabel() {
		return categoryLabel;
	}

	public void setCategoryLabel(String categoryLabel) {
		this.categoryLabel = categoryLabel;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
