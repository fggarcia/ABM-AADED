package ar.edu.utn.aadeed.view;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public class JAViewSession {

	static final Logger Log = LoggerFactory.getLogger(JAViewSession.class);

	private JASession<?> session;

	private JAViewModule viewModule;
	
	private JAViewContainer container;
	
	public JAViewSession(JASession<?> session, JAViewModule viewModule) {
		this.session = session;
		this.viewModule = viewModule;
	}
	
	public JAViewSession withContainer(JAViewContainer container) {
		this.container = container;
		return this;
	}
	
	public void render() {

		Log.info(String.format("Rendering with view module %s", viewModule.getName()));
		
		checkArgument(container != null, "container cannot be null");
		
		List<JAFieldDescription> fieldDescriptions = session.getFieldDescriptions();
		for (JAFieldDescription field : fieldDescriptions) {
			renderFieldDescription(field);
		}
		
		container.render();
	}
	
	private void renderFieldDescription(JAFieldDescription field) {
		
		JAViewDescription viewDescription = field.getView();
		JAViewComponent viewComponent = viewModule.findComponent(viewDescription.getType());
		
		if (viewComponent != null) {
			
			Log.info(String.format("Rendering field %s with type %s", field.getName(), viewDescription.getType()));
			viewComponent.render(field, container);
		}
	}
}