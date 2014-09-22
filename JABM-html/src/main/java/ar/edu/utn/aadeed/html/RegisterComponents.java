package ar.edu.utn.aadeed.html;

import ar.edu.utn.aadeed.html.component.TextBoxComponent;
import ar.edu.utn.aadeed.session.JASessionFactory;

public final class RegisterComponents {

	static {
		JASessionFactory.getInstance().getViewModuleBuilder()
				.addViewComponent(new TextBoxComponent())
				.withName("HtmlModule").register();
	}
}