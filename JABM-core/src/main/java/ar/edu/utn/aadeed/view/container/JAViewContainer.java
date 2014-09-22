package ar.edu.utn.aadeed.view.container;

import ar.edu.utn.aadeed.view.component.JAViewComponent;

public interface JAViewContainer {
	
	public void start();
	
	public void addMember(JAViewComponent member);
	
	public void end();
	
	public void render();

}