package ar.edu.utn.aadeed.view.component;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.container.JAContainer;

public interface JAViewComponent {
	
	public JAViewType getViewType();

	public void render(JAFieldDescription field, JAContainer container);
	
	public void render(Object object, JAFieldDescription field, JAContainer container);

}
