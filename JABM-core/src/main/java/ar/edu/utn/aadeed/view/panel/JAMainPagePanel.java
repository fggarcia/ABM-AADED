package ar.edu.utn.aadeed.view.panel;

import static com.google.common.base.Preconditions.checkArgument;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import ar.edu.utn.aadeed.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.container.frame.JAViewContainer;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTable;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTableBuilder;
import ar.edu.utn.aadeed.view.panel.search.JAViewSearchPanel;

public class JAMainPagePanel {

	private JAViewRecordTableBuilder tableBuilder;
	
	private JAViewSearchPanel searchPanel;
	
	private JAViewContainer container;
	
	private JAViewActionButton delete;
	
	private List<JAFieldDescription> fields;
	
	public JAMainPagePanel withContainer(JAViewContainer container) {
		this.container = container;
		return this;
	}
	
	public JAMainPagePanel withSearchPanel(JAViewSearchPanel searchPanel) {
		this.searchPanel = searchPanel;
		return this;
	}

	public JAMainPagePanel withTableBuilder(JAViewRecordTableBuilder tableBuilder) {
		this.tableBuilder = tableBuilder;
		return this;
	}
	
	public JAMainPagePanel withDeleteActionButton(JAViewActionButton delete) {
		this.delete = delete;
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
		checkArgument(searchPanel != null, "searchPanel cannot be null");
		checkArgument(fields != null, "fields cannot be null");

		searchPanel.render(container);
		
		container.addMember(new JSeparator());
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 10));
		container.addMember(panel);
		
		JAViewRecordTable<T> table = tableBuilder.build();
		table.setColumns(fields);
		table.refresh(filtersBuilder.search());
		table.render(container);
		
		delete.render(container, table);
		
		container.render();
	}
}