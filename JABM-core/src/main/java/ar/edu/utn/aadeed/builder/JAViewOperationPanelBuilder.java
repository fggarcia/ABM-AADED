package ar.edu.utn.aadeed.builder;

import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.panel.JAViewOperationPanel;

public interface JAViewOperationPanelBuilder {

    public <T> JAViewOperationPanel<T> build(JAMainPagePanel<T> mainPagePanel);

}
