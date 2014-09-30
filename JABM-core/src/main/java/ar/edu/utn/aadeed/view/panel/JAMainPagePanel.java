package ar.edu.utn.aadeed.view.panel;

import static com.google.common.base.Preconditions.checkArgument;

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

		checkArgument(filtersBuilder != null, "filtersBuilder cannot be null");
		
		checkArgument(tableBuilder != null, "tableBuilder cannot be null");
		checkArgument(container != null, "mainContainer cannot be null");
		checkArgument(fields != null, "fields cannot be null");
		
		JAViewRecordTable<T> table = tableBuilder.build();
		
		table.setColumns(fields);
		table.refresh(filtersBuilder.search());
		table.render(container);
		
		container.render();
	}
}