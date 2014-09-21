package ar.edu.utn.aadeed.view;

import java.util.Map;

import com.google.common.collect.Maps;

public class ViewModule {

	private String name;
	
	private Map<ViewComponent, ViewComponentBehaviour> behaviours = Maps.newHashMap();

	public ViewModule(String name) {
		this.name = name;
	}
	
	public void addViewComponent(ViewComponentBehaviour behaviour) {
		behaviours.put(behaviour.getViewComponent(), behaviour);
	}

	public String getName() {
		return name;
	}
}
