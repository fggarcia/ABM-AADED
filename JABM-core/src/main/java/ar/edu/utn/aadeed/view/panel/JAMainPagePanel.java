package ar.edu.utn.aadeed.view.panel;

import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTable;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTableBuilder;

public class JAMainPagePanel<T> {

	private JAViewRecordTableBuilder tableBuilder;

	public JAMainPagePanel(JAViewRecordTableBuilder tableBuilder) {
		this.tableBuilder = tableBuilder;
	}
	
	public void render(JAViewContainer container) {
		JAViewRecordTable<T> table = tableBuilder.build();
		table.render(container);
		container.render();
	}
}