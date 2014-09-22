package ar.edu.utn.aadeed.view;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.model.ViewDescription;
import ar.edu.utn.aadeed.session.Session;
import ar.edu.utn.aadeed.view.component.ViewComponentBehaviour;
import ar.edu.utn.aadeed.view.container.ViewContainer;

public class ViewSession<T> {

	static final Logger Log = LoggerFactory.getLogger(ViewSession.class);

	private Session<T> session;

	private ViewModule viewModule;
	
	private ViewContainer container;
	
	private T object;

	public ViewSession(Session<T> session, ViewModule viewModule) {
		this.session = session;
		this.viewModule = viewModule;
	}
	
	public ViewSession<T> withContainer(ViewContainer container) {
		this.container = container;
		return this;
	}
	
	public ViewSession<T> withObject(T object) {
		this.object = object;
		return this;
	}

	public void render() {

		Log.info(String.format("Rendering with view module %s", viewModule.getName()));
		
		checkArgument(container != null, "container cannot be null");
		
		List<FieldDescription> fieldDescriptions = session.getFieldDescriptions();
		for (FieldDescription field : fieldDescriptions) {
			renderFieldDescription(field);
		}
		
		container.render();
	}
	
	private void renderFieldDescription(FieldDescription field) {
		ViewDescription view = field.getView();
		ViewComponentBehaviour behaviour = viewModule.findComponentBehaviour(view.getComponent());
		if (behaviour != null) {
			Log.info(String.format("Rendering field %s with component %s", field.getName(), view.getComponent()));
			behaviour.render(field, container);
		}
	}
}