package ar.edu.utn.aadeed.view.builder;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import ar.edu.utn.aadeed.session.JASessionFactory;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.panel.builder.JAAddPagePanelBuilder;
import ar.edu.utn.aadeed.view.panel.builder.JAMainPagePanelBuilder;
import ar.edu.utn.aadeed.view.panel.builder.JAUpdatePagePanelBuilder;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class JAViewModuleBuilder {
	
	private JASessionFactory sessionFactory;
	
	private String name;
	
	private List<JAViewComponent> viewComponents = Lists.newArrayList();
	
	private JAMainPagePanelBuilder mainPagePanelBuilder;
	
	private JAAddPagePanelBuilder addPagePanelBuilder;
	
	private JAUpdatePagePanelBuilder updatePagePanelBuilder;
	
	public JAViewModuleBuilder(JASessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public JAViewModuleBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public JAViewModuleBuilder withMainPagePanelBuilder(JAMainPagePanelBuilder mainPagePanelBuilder) {
		this.mainPagePanelBuilder = mainPagePanelBuilder;
		return this;
	}
	
	public JAViewModuleBuilder withAddPagePanelBuilder(JAAddPagePanelBuilder addPagePanelBuilder) {
		this.addPagePanelBuilder = addPagePanelBuilder;
		return this;
	}

	public JAViewModuleBuilder withUpdatePagePanelBuilder(JAUpdatePagePanelBuilder updatePagePanelBuilder) {
		this.updatePagePanelBuilder = updatePagePanelBuilder;
		return this;
	}
	
	public JAViewModuleBuilder addViewComponent(JAViewComponent viewComponent) {
		checkArgument(viewComponent != null, "view component cannot be null");
		checkArgument(viewComponent.getViewType() != null, "view type cannot be null");
		viewComponents.add(viewComponent);
		return this;
	}
	
	public void register() {
		this.sessionFactory.registerViewModule(build());
	}
	
	private JAViewModule build() {

		checkArgument(!Strings.isNullOrEmpty(name), "name is mandatory");
		checkArgument(mainPagePanelBuilder != null, "mainPagePanel is mandatory");
		checkArgument(addPagePanelBuilder != null, "addPagePanelBuilder is mandatory");
		checkArgument(updatePagePanelBuilder != null, "updatePagePanelBuilder is mandatory");
		
		JAViewModule viewModule = new JAViewModule(name, mainPagePanelBuilder, addPagePanelBuilder, updatePagePanelBuilder);
		for (JAViewComponent viewComponent : viewComponents) {
			viewModule.addViewComponent(viewComponent);
		}
		
		return viewModule;
	}
}