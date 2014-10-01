package ar.edu.utn.aadeed.view.panel;

import ar.edu.utn.aadeed.view.container.frame.JAViewContainer;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTable;

public interface JAViewActionButton {

	public <T> void render(JAViewContainer container, JAViewRecordTable<T> table);

}