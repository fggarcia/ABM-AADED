package ar.edu.utn.aadeed.view.panel.builder;

import static com.google.common.base.Preconditions.checkArgument;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.container.builder.JAViewContainerBuilder;
import ar.edu.utn.aadeed.view.panel.JAAddPagePanel;

public class JAAddPagePanelBuilder {
	
	private JAViewContainerBuilder containerBuilder;
	
	public JAAddPagePanelBuilder withContainerBuilder(JAViewContainerBuilder containerBuilder) {
		this.containerBuilder = containerBuilder;
		return this;
	}
	
	public <T> JAAddPagePanel<T> build(JAViewModule viewModule, JASession<T> session) {
		
        checkArgument(containerBuilder != null, "containerBuilder cannot be null");
		
		checkArgument(viewModule != null, "viewModule cannot be null");
		checkArgument(session != null, "session cannot be null");
		
		JAAddPagePanel<T> addPagePanel = new JAAddPagePanel<T>();
		addPagePanel.setMainContainer(containerBuilder.build());
		addPagePanel.setSession(session);
		addPagePanel.setViewModule(viewModule);
		
		return addPagePanel;
	}
}
