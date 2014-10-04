package ar.edu.utn.aadeed.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;

public class JAViewSession<T> {

	static final Logger Log = LoggerFactory.getLogger(JAViewSession.class);

	private List<JAFieldDescription> fields;

	private JAViewModule viewModule;
	
	private JASession<T> session;
	
	public JAViewSession(List<JAFieldDescription> fields, JAViewModule viewModule, JASession<T> session) {
		this.fields = fields;
		this.viewModule = viewModule;
		this.session = session;
	}
	
	public void renderMainPagePanel(JAFiltersBuilder<T> filtersBuilder) {
		JAMainPagePanel<T> mainPage = viewModule.getMainPagePanelBuilder().build(session, viewModule);
		mainPage.render(fields, filtersBuilder);
	}
}