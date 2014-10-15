package ar.edu.utn.aadeed.view.panel;

import ar.edu.utn.aadeed.session.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public interface JAViewSearchPanel<T> extends JAViewContainer {

	public void render(JAViewContainer container);
	
	public JAFiltersBuilder<T> findFiltersBuilder();
	
}
