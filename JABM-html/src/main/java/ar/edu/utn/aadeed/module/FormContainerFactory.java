package ar.edu.utn.aadeed.module;

import ar.edu.utn.aadeed.view.container.ViewContainer;
import ar.edu.utn.aadeed.view.container.ViewContainerFactory;

public class FormContainerFactory implements ViewContainerFactory {

	public ViewContainer newInstance() {
		return new FormContainer();
	}
}
