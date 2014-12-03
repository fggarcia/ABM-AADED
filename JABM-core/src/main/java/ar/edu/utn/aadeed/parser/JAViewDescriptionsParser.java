package ar.edu.utn.aadeed.parser;

import java.lang.reflect.Field;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import ar.edu.utn.aadeed.annotation.JAView;
import ar.edu.utn.aadeed.exception.JARuntimeException;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.view.component.JAViewType;

public class JAViewDescriptionsParser {

	public JAViewDescription build(final Field field) {

		if (!field.isAnnotationPresent(JAView.class)) {
			return null;
		}

		final JAView view = field.getAnnotation(JAView.class);
		
		validateDeclaredType(field, view.type());
		
		return createViewDescription(view);
	}

	private JAViewDescription createViewDescription(final JAView view) {
		
		JAViewDescription viewDescription = new JAViewDescription(view.type());
		viewDescription.setLabel(view.label());
		viewDescription.setOrder(view.order());

		return viewDescription;
	}
	
	private static void validateDeclaredType(final Field field, final JAViewType viewType) {
		
		final Class<?> declaredType = field.getType();
		
		final Boolean anyType = Iterables.any(Lists.newArrayList(viewType.getCompatibleJavaTypes()), new Predicate<Class<?>>() {
			public boolean apply(Class<?> input) {
				return input.isAssignableFrom(declaredType);
			}
		});
		
		if (!anyType) {
			final String errorMsg = String.format("Class type '%s' is not compatible with view type '%s'", declaredType, viewType);
			throw new JARuntimeException(errorMsg);
		}
	}
}