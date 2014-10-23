package ar.edu.utn.aadeed.view.component;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.container.JAContainer;

public interface JAViewComponent {
	
	public JAViewType getViewType();

	public void renderForSearch(JAFieldDescription field, JAContainer container);
	
	public void renderForUpdate(Object object, JAFieldDescription field, JAContainer container);

	public void renderForAdd(JAFieldDescription field, JAContainer container);
}
