package ar.edu.utn.aadeed.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.session.JAFields;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;

public class JAViewSession<T> {

	static final Logger Log = LoggerFactory.getLogger(JAViewSession.class);

	private JAViewModule viewModule;
	
	private Class<T> representationFor;
	
	private JAFields fields;
	
	public JAViewSession(JAViewModule viewModule, Class<T> representationFor, JAFields fields) {
		this.viewModule = viewModule;
		this.representationFor = representationFor;
		this.fields = fields;
	}

	public void renderMainPagePanel() {
		JAMainPagePanel<T> mainPage = viewModule.getMainPagePanelBuilder().build(viewModule, representationFor, fields);
		mainPage.render();
	}
}