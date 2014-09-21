package ar.edu.utn.aadeed.session.field;

import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.annotation.Descriptor;

import com.google.common.collect.Lists;

public class FieldsBuilder {

	static final Logger Log = LoggerFactory.getLogger(FieldsBuilder.class);

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
			FieldDescription fieldDescription = new FieldDescription(field.getName());
			fieldDescription.setEditable(descriptor.editable());
			fieldDescription.setFilter(descriptor.filter());
			fieldDescription.setRequired(descriptor.required());

			result.add(fieldDescription);
		}
	}
}