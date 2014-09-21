package ar.edu.utn.aadeed;

import ar.edu.utn.aadeed.module.FormContainerFactory;
import ar.edu.utn.aadeed.module.TextBoxComponent;
import ar.edu.utn.aadeed.session.SessionFactory;

public final class RegisterComponents {
	
	public static final String NAME = "HtmlModule";
	
	static {
		SessionFactory.getInstance().getViewModuleBuilder()
				.addViewComponentBehaviour(new TextBoxComponent())
				.withViewContainerFactory(new FormContainerFactory())
				.withName(NAME).register();
	}
}