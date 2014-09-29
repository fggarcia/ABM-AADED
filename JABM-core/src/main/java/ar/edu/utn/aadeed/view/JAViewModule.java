package ar.edu.utn.aadeed.view;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;

import com.google.common.collect.Maps;

public class JAViewModule {
	
	private String name;

	private Map<JAViewType, JAViewComponent> viewComponents = Maps.newHashMap();
	
	private JAMainPagePanel mainPagePanel;
	
	public JAViewModule(String name, JAMainPagePanel mainPagePanel) {
		this.name = name;
		this.mainPagePanel = mainPagePanel;
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
	
	public JAMainPagePanel getMainPagePanel() {
		return mainPagePanel;
	}
}
