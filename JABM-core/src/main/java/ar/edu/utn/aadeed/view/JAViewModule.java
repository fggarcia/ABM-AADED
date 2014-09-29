package ar.edu.utn.aadeed.view;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTableBuilder;

import com.google.common.collect.Maps;

public class JAViewModule {
	
	private String name;

	private Map<JAViewType, JAViewComponent> viewComponents = Maps.newHashMap();
	
	private JAViewRecordTableBuilder tableBuilder;
	
	public JAViewModule(String name, JAViewRecordTableBuilder tableBuilder) {
		this.name = name;
		this.tableBuilder = tableBuilder;
	}
	
	public void addViewComponent(JAViewComponent viewComponent) {
		checkArgument(viewComponent != null, "view component cannot be null");
		viewComponents.put(viewComponent.getViewType(), viewComponent);
	}
	
	public JAViewComponent findComponent(JAViewType viewType) {
		return this.viewComponents.get(viewType);
	}

	public String getName() {
		return name;
	}
	
	public JAViewRecordTableBuilder getTableBuilder() {
		return tableBuilder;
	}
}
