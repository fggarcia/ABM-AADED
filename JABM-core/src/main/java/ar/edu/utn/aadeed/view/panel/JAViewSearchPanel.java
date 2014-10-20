package ar.edu.utn.aadeed.view.panel;

import ar.edu.utn.aadeed.session.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.view.container.JAContainer;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public interface JAViewSearchPanel<T> extends JAContainer {

	public void renderIn(JAViewContainer container);
	
	public JAFiltersBuilder<T> getFiltersBuilder();
	
}
