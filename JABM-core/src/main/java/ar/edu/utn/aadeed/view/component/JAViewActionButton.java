package ar.edu.utn.aadeed.view.component;

import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.table.JAViewRecordTable;

public interface JAViewActionButton {

	public <T> void render(JAViewContainer container, JAViewRecordTable<T> table);

}