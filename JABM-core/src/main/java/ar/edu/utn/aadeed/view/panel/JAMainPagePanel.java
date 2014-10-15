package ar.edu.utn.aadeed.view.panel;

import static com.google.common.base.Preconditions.checkArgument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.session.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.table.JAViewRecordTable;

import com.google.common.collect.Lists;

public class JAMainPagePanel<T> {

	static final Logger Log = LoggerFactory.getLogger(JAMainPagePanel.class);
	
	private JAViewRecordTable<T> table;
	
	private JAViewSearchPanel<T> searchPanel;

    private JAViewOperationPanel<T> operationPanel;
	
	private JAViewContainer mainContainer;
	
	private JAViewModule viewModule;
	
	private JASession<T> session;
	
	public void render() {
		
		checkArgument(session != null, "session cannot be null");
		checkArgument(table != null, "table cannot be null");
		checkArgument(searchPanel != null, "searchPanel cannot be null");
        checkArgument(operationPanel != null, "operationPanel cannot be null");
		checkArgument(mainContainer != null, "mainContainer cannot be null");
		checkArgument(viewModule != null, "container cannot be null");

		renderSearchFieldFilters();
		
		table.setColumns(Lists.newArrayList(session.getFields().findFieldsToShow()));
		table.refresh(getFiltersBuilder().search());
		table.render(mainContainer);

        operationPanel.render(mainContainer);

        mainContainer.render();
	}
	
	public JAFiltersBuilder<T> getFiltersBuilder() {
		return session.getFiltersBuilder();
	}
	
	public void refreshTable() {
		table.refresh(searchPanel.findFiltersBuilder().search());
	}
	
	public T getSelectedItem() {
		return table.getSelectedItem();
	}
	
	private void renderSearchFieldFilters() {
		
		for (JAFieldDescription field : session.getFields().findAvailableFilters()) {
			renderFieldDescription(field);
		}
		
		searchPanel.render(mainContainer);
	}
	
	private void renderFieldDescription(JAFieldDescription field) {
		
		JAViewDescription viewDescription = field.getView();
		JAViewComponent viewComponent = viewModule.findComponent(viewDescription.getType());
		
		if (viewComponent != null) {
			
			Log.info(String.format("Rendering field %s with type %s", field.getName(), viewDescription.getType()));
			viewComponent.render(field, searchPanel);
		}
	}
	
	public JASession<T> getSession() {
		return session;
	}
	
	public void setTable(JAViewRecordTable<T> table) {
		this.table = table;
	}

	public void setSearchPanel(JAViewSearchPanel<T> searchPanel) {
		this.searchPanel = searchPanel;
	}

    public void setOperationPanel(JAViewOperationPanel<T> operationPanel) {
        this.operationPanel = operationPanel;
    }

	public void setMainContainer(JAViewContainer mainContainer) {
		this.mainContainer = mainContainer;
	}

	public void setViewModule(JAViewModule viewModule) {
		this.viewModule = viewModule;
	}

	public void setSession(JASession<T> session) {
		this.session = session;
	}
}