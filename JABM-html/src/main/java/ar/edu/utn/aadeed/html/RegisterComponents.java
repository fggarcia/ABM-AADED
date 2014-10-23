package ar.edu.utn.aadeed.html;

import ar.edu.utn.aadeed.session.JASessionFactory;

public final class RegisterComponents {

	static {
		JASessionFactory.getInstance().getViewModuleBuilder()
				.withName("HtmlModule").register();
	}
}