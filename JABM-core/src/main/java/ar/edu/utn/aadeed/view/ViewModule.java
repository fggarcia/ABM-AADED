package ar.edu.utn.aadeed.view;

import java.util.Map;

import ar.edu.utn.aadeed.view.component.ViewComponent;
import ar.edu.utn.aadeed.view.component.ViewComponentBehaviour;
import ar.edu.utn.aadeed.view.container.ViewContainerFactory;

import com.google.common.collect.Maps;

public class ViewModule {
	
	private String name;

	private Map<ViewComponent, ViewComponentBehaviour> behaviours = Maps.newHashMap();
	
	private ViewContainerFactory viewContainerFactory;

	public ViewModule(String name, ViewContainerFactory viewContainerFactory) {
		this.name = name;
		this.viewContainerFactory = viewContainerFactory;
	}
	
	public void addViewComponent(ViewComponentBehaviour behaviour) {
		behaviours.put(behaviour.getViewComponent(), behaviour);
	}

	public String getName() {
		return name;
	}
	
	public ViewContainerFactory getViewContainerFactory() {
		return viewContainerFactory;
	}
}
