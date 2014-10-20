package ar.edu.utn.aadeed.view.container.builder;

import ar.edu.utn.aadeed.view.JAViewSession;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public interface JAViewContainerBuilder {

	public <T> JAViewContainer build(JAViewSession<T> viewSession);
	
}
