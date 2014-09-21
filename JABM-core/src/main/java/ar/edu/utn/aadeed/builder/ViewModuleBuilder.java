package ar.edu.utn.aadeed.builder;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import ar.edu.utn.aadeed.session.SessionFactory;
import ar.edu.utn.aadeed.view.ViewModule;
import ar.edu.utn.aadeed.view.component.ViewComponentBehaviour;
import ar.edu.utn.aadeed.view.container.ViewContainerFactory;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class ViewModuleBuilder {
	
	private SessionFactory sessionFactory;
	
	private String name;
	
	private List<ViewComponentBehaviour> behaviours = Lists.newArrayList();
	
	private ViewContainerFactory viewContainerFactory;

	public ViewModuleBuilder(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public ViewModuleBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public ViewModuleBuilder withViewContainerFactory(ViewContainerFactory viewContainerFactory) {
		this.viewContainerFactory = viewContainerFactory;
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

		if (Strings.isNullOrEmpty(name)) {
			throw new IllegalArgumentException("name is mandatory");
		}
		
		if (viewContainerFactory == null) {
			throw new IllegalArgumentException("viewContainerFactory is mandatory");
		}
		
		ViewModule viewModule = new ViewModule(name, viewContainerFactory);
		for (ViewComponentBehaviour behaviour : behaviours) {
			viewModule.addViewComponent(behaviour);
		}
		
		return viewModule;
	}
}
