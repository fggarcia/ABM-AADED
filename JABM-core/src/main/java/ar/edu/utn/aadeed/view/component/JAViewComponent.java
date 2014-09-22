package ar.edu.utn.aadeed.view.component;

import ar.edu.utn.aadeed.model.JAFieldDescription;

public interface JAViewComponent {
	
	public JAViewType getViewType();

	public void render(JAFieldDescription field);
	
	public void render(Object object, JAFieldDescription field);

}
