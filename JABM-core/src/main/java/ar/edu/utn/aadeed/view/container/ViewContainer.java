package ar.edu.utn.aadeed.view.container;

public interface ViewContainer<T> {
	
	public void start();
	
	public void addMember(T member);
	
	public void end();
	
	public void render();

}