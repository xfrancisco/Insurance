package org.insurance.out;

import java.io.Serializable;
import java.util.List;

public class EntityOut extends CodeTableOut implements Serializable {

	private static final long serialVersionUID = 2695116003256677670L;
	private List<String> authorizedParents;
	private List<String> authorizedChildren;

	public List<String> getAuthorizedChildren() {
		return authorizedChildren;
	}

	public void setAuthorizedChildren(List<String> authorizedChildren) {
		this.authorizedChildren = authorizedChildren;
	}

	public List<String> getAuthorizedParents() {
		return authorizedParents;
	}

	public void setAuthorizedParents(List<String> authorizedParents) {
		this.authorizedParents = authorizedParents;
	}

}