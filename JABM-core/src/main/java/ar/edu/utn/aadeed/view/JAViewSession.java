package ar.edu.utn.aadeed.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.session.JAFields;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.container.JAContainer;
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

	public T renderAddPanel() {
		JAAddPagePanel<T> addPagePanel = viewModule.getAddPagePanelBuilder().build(this);
		return addPagePanel.render();
	}

	public T renderUpdatePanel(T item) {
		JAUpdatePagePanel<T> updatePagePanel = viewModule.getUpdatePagePanelBuilder().build(this);
		return updatePagePanel.render(item);
	}

	public void renderFieldForSearch(JAFieldDescription field, JAContainer container) {

		JAViewComponent viewComponent = findViewComponent(field);
		if (viewComponent != null) {
			
			Log.info(String.format("Rendering field %s with type %s", field.getName(), field.getView().getType()));
			viewComponent.renderForSearch(field, container);
		}
	}

	public void renderFieldForAdd(JAFieldDescription field, JAContainer container) {

		JAViewComponent viewComponent = findViewComponent(field);
		if (viewComponent != null) {

			Log.info(String.format("Rendering field %s with type %s", field.getName(), field.getView().getType()));
			viewComponent.renderForAdd(field, container);
		}
	}
	
	public void renderFieldForUpdate(T item, JAFieldDescription field, JAContainer container) {

		JAViewComponent viewComponent = findViewComponent(field);
		if (viewComponent != null) {
			
			Log.info(String.format("Rendering field %s with type %s", field.getName(), field.getView().getType()));
			viewComponent.renderForUpdate(item, field, container);
		}
	}
	
	private JAViewComponent findViewComponent(JAFieldDescription field) {
		JAViewDescription viewDescription = field.getView();
		return viewModule.findComponent(viewDescription.getType());
	}

	public JASession<T> getSession() {
		return session;
	}
	
	public JAFields<T> getFields() {
		return getSession().getFields();
	}
}