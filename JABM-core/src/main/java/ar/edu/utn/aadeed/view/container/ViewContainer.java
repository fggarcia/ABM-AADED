package ar.edu.utn.aadeed.view.container;

public interface ViewContainer {
	
	public void start();
	
	public void addMember(Object member);
	
	public void end();
	
	public void render();

}