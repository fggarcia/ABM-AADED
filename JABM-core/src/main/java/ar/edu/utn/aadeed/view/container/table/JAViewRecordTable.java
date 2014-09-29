package ar.edu.utn.aadeed.view.container.table;

import java.util.List;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.container.frame.JAViewContainer;

public interface JAViewRecordTable<T> {

	public void refresh(List<T> items);

	public void render(JAViewContainer container);

	public void setColumns(List<JAFieldDescription> fields);

}