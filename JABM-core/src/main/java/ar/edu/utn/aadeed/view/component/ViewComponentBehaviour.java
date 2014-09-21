package ar.edu.utn.aadeed.view.component;

import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.view.container.ViewContainer;

public interface ViewComponentBehaviour<T> {

	public ViewComponent getViewComponent();

	public void render(FieldDescription field, ViewContainer<T> container);
}