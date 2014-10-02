package ar.edu.utn.aadeed.view;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import ar.edu.utn.aadeed.builder.JAMainPagePanelBuilder;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;

import com.google.common.collect.Maps;

public class JAViewModule {
	
	private String name;

	private Map<JAViewType, JAViewComponent> viewComponents = Maps.newHashMap();
	
	private JAMainPagePanelBuilder mainPagePanelBuilder;
	
	public JAViewModule(String name, JAMainPagePanelBuilder mainPagePanelBuilder) {
		this.name = name;
		this.mainPagePanelBuilder = mainPagePanelBuilder;
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
	
	public JAMainPagePanelBuilder getMainPagePanelBuilder() {
		return mainPagePanelBuilder;
	}
}
