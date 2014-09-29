package ar.edu.utn.aadeed.view.panel;

import java.util.List;

import ar.edu.utn.aadeed.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.container.frame.JAViewContainer;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTable;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTableBuilder;

public class JAMainPagePanel {

	private JAViewRecordTableBuilder tableBuilder;
	
	private JAViewContainer container;
	
	private List<JAFieldDescription> fields;
	
	public JAMainPagePanel withContainer(JAViewContainer container) {
		this.container = container;
		return this;
	}
	
	public JAMainPagePanel withTableBuilder(JAViewRecordTableBuilder tableBuilder) {
		this.tableBuilder = tableBuilder;
		return this;
	}
	
	public JAMainPagePanel withFields(List<JAFieldDescription> fields) {
		this.fields = fields;
		return this;
	}
	
	public <T> void render(JAFiltersBuilder<T> filtersBuilder) {
		
		JAViewRecordTable<T> table = tableBuilder.build();
		
		table.setColumns(fields);
		table.render(container);
		table.refresh(filtersBuilder.search());
		
		container.render();
	}
}