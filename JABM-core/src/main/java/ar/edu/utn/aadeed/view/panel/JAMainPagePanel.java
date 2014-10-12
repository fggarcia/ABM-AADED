package ar.edu.utn.aadeed.view.panel;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.session.JAFields;
import ar.edu.utn.aadeed.session.JASessionFactory;
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
	
	private JAViewContainer container;
	
	private JAViewModule viewModule;
	
	private Class<T> representationFor;
	
	private JAFields fields;
	
	public void render() {
		
		checkArgument(fields != null, "fields cannot be null");
		checkArgument(table != null, "table cannot be null");
		checkArgument(searchPanel != null, "searchPanel cannot be null");
        checkArgument(operationPanel != null, "operationPanel cannot be null");
		checkArgument(representationFor != null, "representationFor cannot be null");
		checkArgument(container != null, "container cannot be null");
		checkArgument(viewModule != null, "container cannot be null");

		renderSearchFieldFilters();
		
		table.setColumns(Lists.newArrayList(fields.findFielsdsToShow()));
		table.refresh(getFiltersBuilder().search());
		table.render(container);

        operationPanel.render(container);

		container.render();
	}
	
	public JAFiltersBuilder<T> getFiltersBuilder() {
		return JASessionFactory.getInstance().getSession(representationFor).getFiltersBuilder();
	}
	
	public void refreshTable(List<T> items) {
		table.refresh(items);
	}
	
	private void renderSearchFieldFilters() {
		
		for (JAFieldDescription field : fields.findAvailableFilters()) {
			renderFieldDescription(field, searchPanel);
		}
		
		searchPanel.render(container);
	}
	
	private void renderFieldDescription(JAFieldDescription field, JAViewSearchPanel<T> searchPanel) {
		
		JAViewDescription viewDescription = field.getView();
		JAViewComponent viewComponent = viewModule.findComponent(viewDescription.getType());
		
		if (viewComponent != null) {
			
			Log.info(String.format("Rendering field %s with type %s", field.getName(), viewDescription.getType()));
			viewComponent.render(field, searchPanel);
		}
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

	public void setContainer(JAViewContainer container) {
		this.container = container;
	}

	public void setViewModule(JAViewModule viewModule) {
		this.viewModule = viewModule;
	}

	public void setRepresentationFor(Class<T> representationFor) {
		this.representationFor = representationFor;
	}

	public void setFields(JAFields fields) {
		this.fields = fields;
	}
}