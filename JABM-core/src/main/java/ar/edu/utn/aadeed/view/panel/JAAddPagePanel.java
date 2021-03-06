package ar.edu.utn.aadeed.view.panel;

import static com.google.common.base.Preconditions.checkArgument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.JAViewSession;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public class JAAddPagePanel<T> {

	static final Logger Log = LoggerFactory.getLogger(JAAddPagePanel.class);
	
	private JAViewContainer<T> mainContainer;
	
	private JAViewSession<T> viewSession;
	
	public T render() {
		
		checkArgument(viewSession != null, "viewSession cannot be null");
		checkArgument(mainContainer != null, "mainContainer cannot be null");

		renderFields();

		return mainContainer.renderAndReturn();
	}
	
	private void renderFields() {
		for (JAFieldDescription field : viewSession.getSession().getFields().findFieldsToShow()) {
			viewSession.renderFieldForAdd(field, mainContainer);
		}
	}
	
	public void setMainContainer(JAViewContainer<T> mainContainer) {
		this.mainContainer = mainContainer;
	}

	public void setViewSession(JAViewSession<T> viewSession) {
		this.viewSession = viewSession;
	}
}