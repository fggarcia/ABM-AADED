package ar.edu.utn.aadeed.view.panel.builder;

import static com.google.common.base.Preconditions.checkArgument;
import ar.edu.utn.aadeed.view.JAViewSession;
import ar.edu.utn.aadeed.view.container.builder.JAViewContainerBuilder;
import ar.edu.utn.aadeed.view.panel.JAUpdatePagePanel;

public class JAUpdatePagePanelBuilder {
	
	private JAViewContainerBuilder containerBuilder;
	
	public JAUpdatePagePanelBuilder withContainerBuilder(JAViewContainerBuilder containerBuilder) {
		this.containerBuilder = containerBuilder;
		return this;
	}
	
	public <T> JAUpdatePagePanel<T> build(JAViewSession<T> viewSession) {
		
        checkArgument(containerBuilder != null, "containerBuilder cannot be null");
		
		checkArgument(viewSession != null, "viewSession cannot be null");
		
		JAUpdatePagePanel<T> updatePagePanel = new JAUpdatePagePanel<T>();
		updatePagePanel.setMainContainer(containerBuilder.build());
		updatePagePanel.setViewSession(viewSession);
		
		return updatePagePanel;
	}
}
