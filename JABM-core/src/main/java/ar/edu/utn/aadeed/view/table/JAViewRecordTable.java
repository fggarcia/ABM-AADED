package ar.edu.utn.aadeed.view.table;

import java.util.List;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public interface JAViewRecordTable<T> {

	public void refresh(List<T> items);

	public void render(JAViewContainer<T> container);

	public void setColumns(List<JAFieldDescription> fields);
	
	public T getSelectedItem();

}