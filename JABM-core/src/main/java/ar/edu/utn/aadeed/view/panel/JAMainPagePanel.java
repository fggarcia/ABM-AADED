package ar.edu.utn.aadeed.view.panel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.session.JAFields;
import ar.edu.utn.aadeed.session.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.view.JAViewSession;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.table.JAViewRecordTable;

import com.google.common.collect.Lists;

public class JAMainPagePanel<T> {

	static final Logger Log = LoggerFactory.getLogger(JAMainPagePanel.class);
	
	private JAViewRecordTable<T> table;
	
	private JAViewSearchPanel<T> searchPanel;

    private JAViewOperationPanel<T> operationPanel;
	
	private JAViewContainer mainContainer;
	
	private JAViewSession<T> viewSession;
	
	public void render() {
		
		renderSearchFieldFilters();
		
		table.setColumns(Lists.newArrayList(getFields().findFieldsToShow()));
		table.refresh(getFiltersBuilder().search());
		table.render(mainContainer);

        operationPanel.renderIn(mainContainer);

        mainContainer.render();
	}

    public JAFiltersBuilder<T> getFiltersBuilder() {
		return viewSession.getSession().getFiltersBuilder();
	}
	
	public void refreshTable() {
		table.refresh(searchPanel.getFiltersBuilder().search());
	}
	
	public T getSelectedItem() {
		return table.getSelectedItem();
	}
	
	private void renderSearchFieldFilters() {
		for (JAFieldDescription field : getFields().findAvailableFilters()) {
			viewSession.renderField(field, searchPanel);
		}
		searchPanel.renderIn(mainContainer);
	}
	
	public JAFields getFields() {
		return viewSession.getFields();
	}

	public JAViewSession<T> getViewSession() {
		return viewSession;
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

	public void setViewSession(JAViewSession<T> viewSession) {
		this.viewSession = viewSession;
	}
}