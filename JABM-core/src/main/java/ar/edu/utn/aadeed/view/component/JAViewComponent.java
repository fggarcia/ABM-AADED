package ar.edu.utn.aadeed.view.component;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.container.frame.JAViewContainer;

public interface JAViewComponent {
	
	public JAViewType getViewType();

	public void render(JAFieldDescription field, JAViewContainer container);
	
	public void render(Object object, JAFieldDescription field, JAViewContainer container);

}
