package ar.edu.utn.aadeed.builder;

import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.panel.JAViewSearchPanel;

public interface JAViewSearchPanelBuilder {
	
	public <T> JAViewSearchPanel<T> build(JAMainPagePanel<T> mainPagePanel);

}
