package ar.edu.utn.aadeed.view.container;

public interface JAViewContainer<T> extends JAContainer {

	public void render();

	public T renderAndReturn();
	
}