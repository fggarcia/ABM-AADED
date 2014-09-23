package ar.edu.utn.aadeed.html.component;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public class TextBoxComponent implements JAViewComponent {

	public JAViewType getViewType() {
		return JAViewType.TEXT_BOX;
	}

	public void render(JAFieldDescription field, JAViewContainer container) {

		int size = field.getView().getSize();

		StringBuilder sb = new StringBuilder();
		sb.append(field.getLabel());
		sb.append(" : ");
		sb.append("<input type=\"text\" ");
		sb.append(String.format("name=\"%s\" ", field.getName()));
		if (size > 0) sb.append(String.format("maxlength=\"%s\" ", size));
		sb.append(">");
		
		container.addMember(sb.toString());
	}

	public void render(Object object, JAFieldDescription field, JAViewContainer container) {
	}
}