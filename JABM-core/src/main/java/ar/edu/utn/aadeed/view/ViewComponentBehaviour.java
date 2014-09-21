package ar.edu.utn.aadeed.view;

import com.google.common.base.Objects;

import ar.edu.utn.aadeed.model.FieldDescription;

public abstract class ViewComponentBehaviour {

	abstract public ViewComponent getViewComponent();

	abstract public void render(FieldDescription field, ViewContainer container);

	protected String getEffectiveName(FieldDescription field) {
		String label = field.getView().getLabel();
		return Objects.firstNonNull(label, field.getName());
	}
}