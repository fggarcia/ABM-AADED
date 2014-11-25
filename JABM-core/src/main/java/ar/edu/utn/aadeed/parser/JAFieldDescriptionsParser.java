package ar.edu.utn.aadeed.parser;

import java.lang.reflect.Field;
import java.util.List;

import com.google.common.collect.Lists;

import ar.edu.utn.aadeed.annotation.JADescriptor;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.session.JAFields;

public class JAFieldDescriptionsParser {

	private final JAViewDescriptionsParser viewDescriptionsParser = new JAViewDescriptionsParser();
	
	public <T> JAFields build(Class<T> clazz) {

		List<JAFieldDescription> fieldDescriptions = Lists.newArrayList();
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			buildField(field, fieldDescriptions);
		}
		
		return new JAFields(fieldDescriptions);
	}

	private void buildField(Field field, List<JAFieldDescription> fieldDescriptions) {

		if (field.isAnnotationPresent(JADescriptor.class)) {

			JADescriptor descriptor = field.getAnnotation(JADescriptor.class);
			
			JAViewDescription viewDescription = viewDescriptionsParser.build(field);
			
			JAFieldDescription fieldDescription = new JAFieldDescription(field.getName(), field.getType(), viewDescription);
			fieldDescription.setEditable(descriptor.editable());
			fieldDescription.setFilter(descriptor.filter());
			fieldDescription.setRequired(descriptor.required());

			fieldDescriptions.add(fieldDescription);
		}
	}
}