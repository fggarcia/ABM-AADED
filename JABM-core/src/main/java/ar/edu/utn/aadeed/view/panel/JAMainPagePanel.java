package ar.edu.utn.aadeed.view.panel;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.builder.JAViewSearchPanelBuilder;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.table.JAViewRecordTable;
import ar.edu.utn.aadeed.view.table.JAViewRecordTableBuilder;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class JAMainPagePanel<T> {

	static final Logger Log = LoggerFactory.getLogger(JAMainPagePanel.class);
	
	private JAViewRecordTableBuilder tableBuilder;
	
	private JAViewSearchPanelBuilder searchPanelBuilder;
	
	private JAViewContainer container;
	
	private JAViewModule viewModule;
	
	public JAMainPagePanel(JAViewRecordTableBuilder tableBuilder, JAViewSearchPanelBuilder searchPanelBuilder, JAViewContainer container, JAViewModule viewModule) {
		this.tableBuilder = tableBuilder;
		this.searchPanelBuilder = searchPanelBuilder;
		this.container = container;
		this.viewModule = viewModule;
	}

	public void render(List<JAFieldDescription> fields, JAFiltersBuilder<T> filtersBuilder) {

		checkArgument(filtersBuilder != null, "filtersBuilder cannot be null");
		checkArgument(fields != null, "fields cannot be null");

		JAViewSearchPanel searchPanel = searchPanelBuilder.build();
		renderSearchFieldFilters(fields, searchPanel);
		searchPanel.render(container);
		
		JAViewRecordTable<T> table = tableBuilder.build();
		table.setColumns(fields);
		table.refresh(filtersBuilder.search());
		table.render(container);
		
		container.render();
	}
	
	private void renderSearchFieldFilters(List<JAFieldDescription> fields, JAViewSearchPanel searchPanel) {
		for (JAFieldDescription field : findSearchFilters(fields)) {
			renderFieldDescription(field, searchPanel);
		}
	}
	
	private void renderFieldDescription(JAFieldDescription field, JAViewSearchPanel searchPanel) {
		
		JAViewDescription viewDescription = field.getView();
		JAViewComponent viewComponent = viewModule.findComponent(viewDescription.getType());
		
		if (viewComponent != null) {
			
			Log.info(String.format("Rendering field %s with type %s", field.getName(), viewDescription.getType()));
			viewComponent.render(field, searchPanel);
		}
	}
	
	private Iterable<JAFieldDescription> findSearchFilters(List<JAFieldDescription> fields) {
		return Iterables.filter(fields, new Predicate<JAFieldDescription>() {
			public boolean apply(JAFieldDescription input) {
				return input.isFilter();
			}
		});
	}
}