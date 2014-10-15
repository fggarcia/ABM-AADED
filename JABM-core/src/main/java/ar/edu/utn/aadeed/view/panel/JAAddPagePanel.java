package ar.edu.utn.aadeed.view.panel;

import static com.google.common.base.Preconditions.checkArgument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public class JAAddPagePanel<T> {

	static final Logger Log = LoggerFactory.getLogger(JAAddPagePanel.class);
	
	private JAViewContainer mainContainer;
	
	private JAViewModule viewModule;
	
	private JASession<T> session;
	
	public void render() {
		
		checkArgument(session != null, "session cannot be null");
		checkArgument(mainContainer != null, "mainContainer cannot be null");
		checkArgument(viewModule != null, "container cannot be null");

		renderFields();
		
        mainContainer.render();
	}
	
	private void renderFields() {
		
		for (JAFieldDescription field : session.getFields().findFieldsToShow()) {
			renderFieldDescription(field);
		}
		
		mainContainer.render();
	}
	
	private void renderFieldDescription(JAFieldDescription field) {
		
		JAViewDescription viewDescription = field.getView();
		JAViewComponent viewComponent = viewModule.findComponent(viewDescription.getType());
		
		if (viewComponent != null) {
			
			Log.info(String.format("Rendering field %s with type %s", field.getName(), viewDescription.getType()));
			viewComponent.render(field, mainContainer);
		}
	}
	
	public void setMainContainer(JAViewContainer mainContainer) {
		this.mainContainer = mainContainer;
	}

	public void setViewModule(JAViewModule viewModule) {
		this.viewModule = viewModule;
	}

	public void setSession(JASession<T> session) {
		this.session = session;
	}
}