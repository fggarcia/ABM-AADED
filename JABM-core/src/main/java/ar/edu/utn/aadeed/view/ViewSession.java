package ar.edu.utn.aadeed.view;

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

	public ViewSession(Session<T> session, ViewModule viewModule) {
		this.session = session;
		this.viewModule = viewModule;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void renderIn(ViewContainer container) {
		Log.info(String.format("Render with view module %s", viewModule.getName()));

		List<FieldDescription> fieldDescriptions = session.getFieldDescriptions();
		for (FieldDescription field : fieldDescriptions) {
			ViewDescription view = field.getView();
			ViewComponentBehaviour behaviour = viewModule.findComponentBehaviour(view.getComponent());
			if (behaviour != null) {
				behaviour.render(field, container);
			}
		}

		container.render();
	}
}