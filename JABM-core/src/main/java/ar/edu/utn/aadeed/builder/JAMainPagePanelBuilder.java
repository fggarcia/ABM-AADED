package ar.edu.utn.aadeed.builder;

import static com.google.common.base.Preconditions.checkArgument;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.table.JAViewRecordTableBuilder;

public class JAMainPagePanelBuilder {
	
	private JAViewRecordTableBuilder tableBuilder;
	
	private JAViewSearchPanelBuilder searchPanelBuilder;
	
	private JAViewContainer container;
	
	public JAMainPagePanelBuilder withContainer(JAViewContainer container) {
		this.container = container;
		return this;
	}
	
	public JAMainPagePanelBuilder withSearchPanelBuilder(JAViewSearchPanelBuilder searchPanelBuilder) {
		this.searchPanelBuilder = searchPanelBuilder;
		return this;
	}

	public JAMainPagePanelBuilder withTableBuilder(JAViewRecordTableBuilder tableBuilder) {
		this.tableBuilder = tableBuilder;
		return this;
	}
	
	public <T> JAMainPagePanel<T> build(JAViewModule viewModule) {
		
		checkArgument(tableBuilder != null, "tableBuilder cannot be null");
		checkArgument(searchPanelBuilder != null, "searchPanelBuilder cannot be null");
		checkArgument(container != null, "container cannot be null");
		checkArgument(viewModule != null, "viewModule cannot be null");
		
		return new JAMainPagePanel<T>(tableBuilder, searchPanelBuilder, container, viewModule);
	}
}
