package ar.edu.utn.aadeed.view;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import ar.edu.utn.aadeed.view.component.ViewComponent;
import ar.edu.utn.aadeed.view.component.ViewComponentBehaviour;

import com.google.common.collect.Maps;

public class ViewModule {
	
	private String name;

	private Map<ViewComponent, ViewComponentBehaviour> behaviours = Maps.newHashMap();
	
	public ViewModule(String name) {
		this.name = name;
	}
	
	public void addViewComponent(ViewComponentBehaviour behaviour) {
		checkArgument(behaviour != null, "behaviour cannot be null");
		behaviours.put(behaviour.getViewComponent(), behaviour);
	}
	
	public ViewComponentBehaviour findComponentBehaviour(ViewComponent component) {
		return this.behaviours.get(component);
	}

	public String getName() {
		return name;
	}
}
