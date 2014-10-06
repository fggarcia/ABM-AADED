package ar.edu.utn.aadeed.builder;

import static com.google.common.base.Preconditions.checkArgument;
import ar.edu.utn.aadeed.session.JAFields;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.container.JAViewContainerBuilder;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.table.JAViewRecordTableBuilder;

public class JAMainPagePanelBuilder {
	
	private JAViewRecordTableBuilder tableBuilder;
	
	private JAViewSearchPanelBuilder searchPanelBuilder;
	
	private JAViewContainerBuilder containerBuilder;
	
	public JAMainPagePanelBuilder withContainerBuilder(JAViewContainerBuilder containerBuilder) {
		this.containerBuilder = containerBuilder;
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
	
	public <T> JAMainPagePanel<T> build(JAViewModule viewModule, Class<T> representationFor, JAFields fields) {
		
		checkArgument(tableBuilder != null, "tableBuilder cannot be null");
		checkArgument(searchPanelBuilder != null, "searchPanelBuilder cannot be null");
		checkArgument(containerBuilder != null, "containerBuilder cannot be null");
		
		checkArgument(viewModule != null, "viewModule cannot be null");
		checkArgument(representationFor != null, "representationFor cannot be null");
		checkArgument(fields != null, "fields cannot be null");
		
		JAMainPagePanel<T> mainPagePanel = new JAMainPagePanel<T>();
		mainPagePanel.setContainer(containerBuilder.build());
		mainPagePanel.setFields(fields);
		mainPagePanel.setRepresentationFor(representationFor);
		mainPagePanel.setSearchPanel(searchPanelBuilder.<T>build(mainPagePanel));
		mainPagePanel.setTable(tableBuilder.<T>build());
		mainPagePanel.setViewModule(viewModule);
		
		return mainPagePanel;
	}
}
