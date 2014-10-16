package ar.edu.utn.aadeed.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.panel.JAAddPagePanel;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.panel.JAUpdatePagePanel;

public class JAViewSession<T> {

	static final Logger Log = LoggerFactory.getLogger(JAViewSession.class);

	private JAViewModule viewModule;
	
	private JASession<T> session;
	
	public JAViewSession(JAViewModule viewModule, JASession<T> session) {
		this.viewModule = viewModule;
		this.session = session;
	}

	public void renderMainPagePanel() {
		JAMainPagePanel<T> mainPage = viewModule.getMainPagePanelBuilder().build(this);
		mainPage.render();
	}
	
	public void renderAddPanel() {
		JAAddPagePanel<T> addPagePanel = viewModule.getAddPagePanelBuilder().build(this);
		addPagePanel.render();
	}
	
	public void renderUpdatePanel(T item) {
		JAUpdatePagePanel<T> updatePagePanel = viewModule.getUpdatePagePanelBuilder().build(this);
		updatePagePanel.render(item);
	}

    public JASession<T> getSession() {
        return session;
    }

    public JAViewModule getViewModule() {
        return viewModule;
    }
}