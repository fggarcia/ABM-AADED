package ar.edu.utn.aadeed.view;

import ar.edu.utn.aadeed.session.Session;
import ar.edu.utn.aadeed.view.container.ViewContainer;

public class ViewSession<T> {

	private Session<T> session;

	private ViewModule viewModule;

	public ViewSession(Session<T> session, ViewModule viewModule) {
		this.session = session;
		this.viewModule = viewModule;
	}

	public ViewContainer getBeanFields() {
		
		ViewContainer container = viewModule.getViewContainerFactory().newInstance();
		
		container.start();
		
		container.end();
		
		return container;
	}
}