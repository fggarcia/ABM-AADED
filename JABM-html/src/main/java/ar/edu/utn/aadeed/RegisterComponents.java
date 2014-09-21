package ar.edu.utn.aadeed;

import ar.edu.utn.aadeed.module.TextBoxComponent;
import ar.edu.utn.aadeed.session.SessionFactory;

public final class RegisterComponents {
	static {
		SessionFactory.getInstance().getViewModuleBuilder()
				.addViewComponentBehaviour(new TextBoxComponent())
				.withName("HtmlModule").register();
	}
}