package ar.edu.utn.aadeed.view.panel;

import static com.google.common.base.Preconditions.checkArgument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.JAViewSession;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public class JAUpdatePagePanel<T> {

	static final Logger Log = LoggerFactory.getLogger(JAUpdatePagePanel.class);
	
	private JAViewContainer mainContainer;
	
	private JAViewSession<T> viewSession;
	
	public void render(T item) {
		
		checkArgument(viewSession != null, "viewSession cannot be null");
		checkArgument(mainContainer != null, "mainContainer cannot be null");

		renderFields(item);
		
        mainContainer.render();
	}
	
	private void renderFields(T item) {
		for (JAFieldDescription field : viewSession.getSession().getFields().findFieldsToShow()) {
			viewSession.renderField(item, field, mainContainer);
		}
	}
	
	public void setMainContainer(JAViewContainer mainContainer) {
		this.mainContainer = mainContainer;
	}

	public void setViewSession(JAViewSession<T> viewSession) {
		this.viewSession = viewSession;
	}
}