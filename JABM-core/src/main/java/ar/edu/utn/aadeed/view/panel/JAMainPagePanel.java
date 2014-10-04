package ar.edu.utn.aadeed.view.panel;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.builder.JAViewSearchPanelBuilder;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.table.JAViewRecordTable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class JAMainPagePanel<T> {

	static final Logger Log = LoggerFactory.getLogger(JAMainPagePanel.class);
	
	private JAViewRecordTable<T> table;
	
	private JAViewSearchPanelBuilder searchPanelBuilder;
	
	private JAViewContainer container;
	
	private JAViewModule viewModule;
	
	private JASession<T> session;
	
	public JAMainPagePanel(JAViewRecordTable<T> table, JAViewSearchPanelBuilder searchPanelBuilder, JAViewContainer container, JAViewModule viewModule, JASession<T> session) {
		this.table = table;
		this.searchPanelBuilder = searchPanelBuilder;
		this.container = container;
		this.viewModule = viewModule;
		this.session = session;
	}

	public void render(List<JAFieldDescription> fields, JAFiltersBuilder<T> filtersBuilder) {

		checkArgument(filtersBuilder != null, "filtersBuilder cannot be null");
		checkArgument(fields != null, "fields cannot be null");
		checkArgument(table != null, "table cannot be null");
		checkArgument(searchPanelBuilder != null, "searchPanel cannot be null");

		JAViewSearchPanel<T> searchPanel = searchPanelBuilder.build(this);
		
		renderSearchFieldFilters(fields, searchPanel);
		searchPanel.render(container);
		
		table.setColumns(fields);
		table.refresh(filtersBuilder.search());
		table.render(container);
		
		container.render();
	}
	
	public void refreshTable(List<T> items) {
		table.refresh(items);
	}
	
	public JAFiltersBuilder<T> getFiltersBuilder() {
		return session.getFiltersBuilder();
	}
	
	private void renderSearchFieldFilters(List<JAFieldDescription> fields, JAViewSearchPanel<T> searchPanel) {
		for (JAFieldDescription field : findSearchFilters(fields)) {
			renderFieldDescription(field, searchPanel);
		}
	}
	
	private void renderFieldDescription(JAFieldDescription field, JAViewSearchPanel<T> searchPanel) {
		
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