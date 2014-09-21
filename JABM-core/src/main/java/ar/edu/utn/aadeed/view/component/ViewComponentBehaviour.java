package ar.edu.utn.aadeed.view.component;

import com.google.common.base.Objects;

import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.view.container.ViewContainer;

public abstract class ViewComponentBehaviour {

	abstract public ViewComponent getViewComponent();

	abstract public void render(FieldDescription field, ViewContainer container);

	protected String getEffectiveName(FieldDescription field) {
		String label = field.getView().getLabel();
		return Objects.firstNonNull(label, field.getName());
	}
}