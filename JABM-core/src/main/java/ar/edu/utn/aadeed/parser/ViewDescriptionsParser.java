package ar.edu.utn.aadeed.parser;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.annotation.View;
import ar.edu.utn.aadeed.model.ViewDescription;

public class ViewDescriptionsParser {

	static final Logger Log = LoggerFactory.getLogger(ViewDescriptionsParser.class);

	public ViewDescription build(Field field) {

		if (!field.isAnnotationPresent(View.class)) {
			return null;
		}

		View view = field.getAnnotation(View.class);
		return createViewDescription(view);
	}

	private ViewDescription createViewDescription(View view) {
		ViewDescription viewDescription = new ViewDescription(view.component());
		viewDescription.setLabel(view.label());
		viewDescription.setOrder(view.order());
		viewDescription.setSize(view.size());
		return viewDescription;
	}
}