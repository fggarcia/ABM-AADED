package ar.edu.utn.aadeed.view.panel.builder;

import static com.google.common.base.Preconditions.checkArgument;
import ar.edu.utn.aadeed.view.JAViewSession;
import ar.edu.utn.aadeed.view.container.builder.JAViewContainerBuilder;
import ar.edu.utn.aadeed.view.panel.JAAddPagePanel;

public class JAAddPagePanelBuilder {
	
	private JAViewContainerBuilder containerBuilder;
	
	public JAAddPagePanelBuilder withContainerBuilder(JAViewContainerBuilder containerBuilder) {
		this.containerBuilder = containerBuilder;
		return this;
	}
	
	public <T> JAAddPagePanel<T> build(JAViewSession<T> viewSession) {
		
        checkArgument(containerBuilder != null, "containerBuilder cannot be null");
		
		checkArgument(viewSession != null, "viewSession cannot be null");
		
		JAAddPagePanel<T> addPagePanel = new JAAddPagePanel<T>();
		addPagePanel.setMainContainer(containerBuilder.build(viewSession));
		addPagePanel.setViewSession(viewSession);
		
		return addPagePanel;
	}
}
