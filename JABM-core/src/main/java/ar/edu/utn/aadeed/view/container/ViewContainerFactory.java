package ar.edu.utn.aadeed.view.container;

public interface ViewContainerFactory<T> {

	public ViewContainer<T> newInstance(Object... params);

}