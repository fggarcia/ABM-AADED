package ar.edu.utn.aadeed.parser;

import java.lang.reflect.Field;
import java.util.List;

import ar.edu.utn.aadeed.annotation.Descriptor;
import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.model.ViewDescription;

import com.google.common.collect.Lists;

public class FieldDescriptionsParser {

	private final ViewDescriptionsParser viewDescriptionsParser = new ViewDescriptionsParser();

	public <T> List<FieldDescription> build(Class<T> clazz) {

		List<FieldDescription> result = Lists.newArrayList();
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			buildField(field, result);
		}
		
		return result;
	}

	private void buildField(Field field, List<FieldDescription> result) {

		if (field.isAnnotationPresent(Descriptor.class)) {

			Descriptor descriptor = field.getAnnotation(Descriptor.class);
			
			ViewDescription viewDescription = viewDescriptionsParser.build(field);
			if (viewDescription == null) {
				throw new IllegalArgumentException(String.format("Field %s must be annotated with View", field.getName()));
			}
			
			FieldDescription fieldDescription = new FieldDescription(field.getName(), field.getType(), viewDescription);
			fieldDescription.setEditable(descriptor.editable());
			fieldDescription.setFilter(descriptor.filter());
			fieldDescription.setRequired(descriptor.required());

			result.add(fieldDescription);
		}
	}
}