package ar.edu.utn.aadeed.parser;

import java.lang.reflect.Field;

import ar.edu.utn.aadeed.annotation.JAView;
import ar.edu.utn.aadeed.model.JAViewDescription;

public class JAViewDescriptionsParser {

	public JAViewDescription build(Field field) {

		if (!field.isAnnotationPresent(JAView.class)) {
			return null;
		}

		JAView view = field.getAnnotation(JAView.class);
		return createViewDescription(view);
	}

	private JAViewDescription createViewDescription(JAView view) {
		JAViewDescription viewDescription = new JAViewDescription(view.type());
		viewDescription.setLabel(view.label());
		viewDescription.setOrder(view.order());
		viewDescription.setSize(view.size());
		return viewDescription;
	}
}