package ar.edu.utn.aadeed.parser;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import ar.edu.utn.aadeed.exception.JARuntimeException;
import com.google.common.collect.Lists;

import ar.edu.utn.aadeed.annotation.JADescriptor;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.session.JAFields;
import org.apache.commons.lang.StringUtils;

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

			final Class<?> clazzType = field.getType();
			
			JAFieldDescription fieldDescription = new JAFieldDescription(field.getName(), field.getType(), viewDescription);
			fieldDescription.setEditable(descriptor.editable());
			fieldDescription.setFilter(descriptor.filter());
			fieldDescription.setRequired(descriptor.required());

			final boolean isStringTypeCompatible = String.class.isAssignableFrom(clazzType);

			validateSize(isStringTypeCompatible, descriptor.size());

			fieldDescription.setSize(descriptor.size());

			validateRegex(isStringTypeCompatible, descriptor.regex());

			fieldDescription.setRegularExpression(descriptor.regex());

			fieldDescriptions.add(fieldDescription);
		}
	}

	private void validateRegex(final boolean isStringTypeCompatible, final String regex) {

		try {
			Pattern.compile(regex);
		} catch (PatternSyntaxException e) {
			throw new JARuntimeException(String.format("regex '%s' is not valid", regex));
		}

		if (StringUtils.isNotBlank(regex) && !isStringTypeCompatible) {
            throw new JARuntimeException("regex feature only applies for String values");
        }
	}

	private void validateSize(boolean isStringTypeCompatible, int size) {

		if (size < 0 && size != -1){
            throw new JARuntimeException("Size value must be greater than Zero");
        }

		if (size != -1 && !isStringTypeCompatible) {
            throw new JARuntimeException("size feature only applies for String values");
        }
	}
}