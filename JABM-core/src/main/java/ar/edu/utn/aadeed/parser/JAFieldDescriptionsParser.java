package ar.edu.utn.aadeed.parser;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.StringUtils;

import ar.edu.utn.aadeed.annotation.JADescriptor;
import ar.edu.utn.aadeed.annotation.JAValidator;
import ar.edu.utn.aadeed.exception.JARuntimeException;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.JAViewDescription;
import ar.edu.utn.aadeed.session.JAFields;
import ar.edu.utn.aadeed.validator.JAOperationValidator;

import com.google.common.collect.Lists;

public class JAFieldDescriptionsParser {

	private final JAViewDescriptionsParser viewDescriptionsParser = new JAViewDescriptionsParser();
	
	public <T> JAFields<T> build(Class<T> clazz) {

		List<JAFieldDescription> fieldDescriptions = Lists.newArrayList();
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			buildField(field, fieldDescriptions);
		}
		
		return new JAFields<T>(fieldDescriptions);
	}

	private void buildField(Field field, List<JAFieldDescription> fieldDescriptions) {

		if (field.isAnnotationPresent(JADescriptor.class)) {

			JADescriptor descriptor = field.getAnnotation(JADescriptor.class);
			
			JAViewDescription viewDescription = viewDescriptionsParser.build(field);

			final Class<?> clazzType = field.getType();
			final boolean isStringTypeCompatible = String.class.isAssignableFrom(clazzType);
			
			final JAFieldDescription fieldDescription = new JAFieldDescription(field.getName(), clazzType, viewDescription);
			fieldDescription.setEditable(descriptor.editable());
			fieldDescription.setFilter(descriptor.filter());
			fieldDescription.setRequired(descriptor.required());

			validateMaxLength(isStringTypeCompatible, descriptor.maxLength());
			fieldDescription.setMaxLength(descriptor.maxLength());

			validateRegex(isStringTypeCompatible, descriptor.regex());
			fieldDescription.setRegularExpression(descriptor.regex());
			
			addValidators(descriptor, fieldDescription);

			fieldDescriptions.add(fieldDescription);
		}
	}

	private void addValidators(final JADescriptor descriptor, final JAFieldDescription fieldDescription) {
		for (JAValidator validator : descriptor.validators()) {
			fieldDescription.addValidator(createValidator(validator.validator()));
		}
	}

	private JAOperationValidator createValidator(final Class<? extends JAOperationValidator> validator) {
		try {
			return validator.newInstance();
		} catch (Exception e) {
			final String errorMsg = String.format("Could not create validator '%s'", validator.getName());
			throw new JARuntimeException(errorMsg);
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

	private void validateMaxLength(boolean isStringTypeCompatible, int maxLength) {

		if (maxLength < 0 && maxLength != -1){
            throw new JARuntimeException("Length value must be greater than Zero");
        }

		if (maxLength != -1 && !isStringTypeCompatible) {
            throw new JARuntimeException("maxLength feature only applies for String values");
        }
	}
}