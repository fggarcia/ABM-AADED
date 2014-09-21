package ar.edu.utn.aadeed.module;

import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.view.component.ViewComponent;
import ar.edu.utn.aadeed.view.component.ViewComponentBehaviour;
import ar.edu.utn.aadeed.view.container.ViewContainer;

public class TextBoxComponent extends ViewComponentBehaviour {

	@Override
	public ViewComponent getViewComponent() {
		return ViewComponent.TEXT_BOX;
	}

	@Override
	public void render(FieldDescription field, ViewContainer container) {

		int size = field.getView().getSize();

		StringBuilder sb = new StringBuilder();
		sb.append(getEffectiveName(field));
		sb.append(" : ");
		sb.append("<input type=\"text\" ");
		sb.append(String.format("name=\"%s\" ", field.getName()));
		if (size > 0) sb.append(String.format("maxlength=\"%s\" ", size));
		sb.append(">");
		
		container.addMember(sb);
	}
}