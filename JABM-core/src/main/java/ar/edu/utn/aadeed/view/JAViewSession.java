package ar.edu.utn.aadeed.view;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTable;

public class JAViewSession<T> {

	static final Logger Log = LoggerFactory.getLogger(JAViewSession.class);

	private List<JAFieldDescription> fields;

	private JAViewModule viewModule;
	
	public JAViewSession(List<JAFieldDescription> fields, JAViewModule viewModule) {
		this.fields = fields;
		this.viewModule = viewModule;
	}

	public void renderRecordTable(JAViewContainer container, List<T> items) {
		JAViewRecordTable<T> table = viewModule.getTableBuilder().build();
		table.setColumns(fields);
		table.render(container);
		table.refresh(items);
		container.render();
	}
	
	public void render(JAViewContainer container) {

		Log.info(String.format("Rendering with view module %s", viewModule.getName()));
		
		checkArgument(container != null, "container cannot be null");
		
		for (JAFieldDescription field : fields) {
			if (field.hasView()) {
				renderFieldDescription(field, container);				
			}
		}
		
		container.render();
	}
	
	private void renderFieldDescription(JAFieldDescription field, JAViewContainer container) {
		
		JAViewDescription viewDescription = field.getView();
		JAViewComponent viewComponent = viewModule.findComponent(viewDescription.getType());
		
		if (viewComponent != null) {
			
			Log.info(String.format("Rendering field %s with type %s", field.getName(), viewDescription.getType()));
			viewComponent.render(field, container);
		}
	}
}