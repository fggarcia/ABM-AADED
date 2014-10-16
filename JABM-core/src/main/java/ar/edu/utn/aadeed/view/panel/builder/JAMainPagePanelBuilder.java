package ar.edu.utn.aadeed.view.panel.builder;

import static com.google.common.base.Preconditions.checkArgument;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.JAViewSession;
import ar.edu.utn.aadeed.view.container.builder.JAViewContainerBuilder;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.table.JAViewRecordTable;
import ar.edu.utn.aadeed.view.table.builder.JAViewRecordTableBuilder;

public class JAMainPagePanelBuilder {
	
	private JAViewRecordTableBuilder tableBuilder;
	
	private JAViewSearchPanelBuilder searchPanelBuilder;

    private JAViewOperationPanelBuilder operationPanelBuilder;
	
	private JAViewContainerBuilder containerBuilder;
	
	public JAMainPagePanelBuilder withContainerBuilder(JAViewContainerBuilder containerBuilder) {
		this.containerBuilder = containerBuilder;
		return this;
	}
	
	public JAMainPagePanelBuilder withSearchPanelBuilder(JAViewSearchPanelBuilder searchPanelBuilder) {
		this.searchPanelBuilder = searchPanelBuilder;
		return this;
	}

    public JAMainPagePanelBuilder withOperationPanelBuilder(JAViewOperationPanelBuilder operationPanelBuilder) {
        this.operationPanelBuilder = operationPanelBuilder;
        return this;
    }

	public JAMainPagePanelBuilder withTableBuilder(JAViewRecordTableBuilder tableBuilder) {
		this.tableBuilder = tableBuilder;
		return this;
	}
	
	public <T> JAMainPagePanel<T> build(JAViewSession<T> viewSession) {
		
		checkArgument(tableBuilder != null, "tableBuilder cannot be null");
		checkArgument(searchPanelBuilder != null, "searchPanelBuilder cannot be null");
        checkArgument(operationPanelBuilder != null, "operationPanelBuilder cannot be null");
        checkArgument(containerBuilder != null, "containerBuilder cannot be null");
		
		checkArgument(viewSession != null, "viewSession cannot be null");
		
		JAViewRecordTable<T> table = tableBuilder.<T>build();
		JAMainPagePanel<T> mainPagePanel = new JAMainPagePanel<T>();
		
		mainPagePanel.setMainContainer(containerBuilder.build());
		mainPagePanel.setViewSession(viewSession);
		mainPagePanel.setSearchPanel(searchPanelBuilder.<T>build(mainPagePanel));
        mainPagePanel.setOperationPanel(operationPanelBuilder.<T>build(mainPagePanel));
		mainPagePanel.setTable(table);

		return mainPagePanel;
	}
}
