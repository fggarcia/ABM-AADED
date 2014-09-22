package ar.edu.utn.aadeed.builder;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import ar.edu.utn.aadeed.session.SessionFactory;
import ar.edu.utn.aadeed.view.ViewModule;
import ar.edu.utn.aadeed.view.component.ViewComponentBehaviour;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class ViewModuleBuilder {
	
	private SessionFactory sessionFactory;
	
	private String name;
	
	private List<ViewComponentBehaviour> behaviours = Lists.newArrayList();
	
	public ViewModuleBuilder(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public ViewModuleBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public ViewModuleBuilder addViewComponentBehaviour(ViewComponentBehaviour behaviour) {
		checkArgument(behaviour != null, "behaviour cannot be null");
		checkArgument(behaviour.getViewComponent() != null, "view component cannot be null");
		behaviours.add(behaviour);
		return this;
	}
	
	public void register() {
		this.sessionFactory.registerViewModule(build());
	}
	
	private ViewModule build() {

		checkArgument(!Strings.isNullOrEmpty(name), "name is mandatory");
		
		ViewModule viewModule = new ViewModule(name);
		for (ViewComponentBehaviour behaviour : behaviours) {
			viewModule.addViewComponent(behaviour);
		}
		
		return viewModule;
	}
}
