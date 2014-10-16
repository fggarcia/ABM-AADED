package ar.edu.utn.aadeed.view;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.panel.builder.JAAddPagePanelBuilder;
import ar.edu.utn.aadeed.view.panel.builder.JAMainPagePanelBuilder;
import ar.edu.utn.aadeed.view.panel.builder.JAUpdatePagePanelBuilder;

import com.google.common.collect.Maps;

public class JAViewModule {
	
	private String name;

	private Map<JAViewType, JAViewComponent> viewComponents = Maps.newHashMap();
	
	private JAMainPagePanelBuilder mainPagePanelBuilder;
	
	private JAAddPagePanelBuilder addPagePanelBuilder;
	
	private JAUpdatePagePanelBuilder updatePagePanelBuilder;
	
	public JAViewModule(String name, JAMainPagePanelBuilder mainPagePanelBuilder, JAAddPagePanelBuilder addPagePanelBuilder, JAUpdatePagePanelBuilder updatePagePanelBuilder) {
		this.name = name;
		this.mainPagePanelBuilder = mainPagePanelBuilder;
		this.addPagePanelBuilder = addPagePanelBuilder;
		this.updatePagePanelBuilder = updatePagePanelBuilder;
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
	
	public JAAddPagePanelBuilder getAddPagePanelBuilder() {
		return addPagePanelBuilder;
	}
	
	public JAUpdatePagePanelBuilder getUpdatePagePanelBuilder() {
		return updatePagePanelBuilder;
	}
}
