package ar.edu.utn.aadeed.module;

import ar.edu.utn.aadeed.view.ViewContainer;

public class FormContainer implements ViewContainer {

	private StringBuffer sb = new StringBuffer();

	public void start() {

	}

	public void addMember(Object member) {
		sb.append(member);
	}

	public void end() {

	}
}
