package ar.edu.utn.aadeed.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.panel.JAAddPagePanel;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;

public class JAViewSession<T> {

	static final Logger Log = LoggerFactory.getLogger(JAViewSession.class);

	private JAViewModule viewModule;
	
	private JASession<T> session;
	
	public JAViewSession(JAViewModule viewModule, JASession<T> session) {
		this.viewModule = viewModule;
		this.session = session;
	}

	public void renderMainPagePanel() {
		JAMainPagePanel<T> mainPage = viewModule.getMainPagePanelBuilder().build(viewModule, session);
		mainPage.render();
	}
	
	public T renderAddPanel() {
		JAAddPagePanel<T> addPagePanel = viewModule.getAddPagePanelBuilder().build(viewModule, session);
		addPagePanel.render();
		return null;
	}
}