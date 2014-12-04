package ar.edu.utn.aadeed.session;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.exception.JARuntimeException;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.comparator.JAFieldDescriptionComparator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

public class JAFields {

	private List<JAFieldDescription> fieldDescriptions = Lists.newArrayList();
	
	public JAFields(List<JAFieldDescription> fieldDescriptions) {
		checkArgument(fieldDescriptions != null, "fieldDescriptions cannot be null");
		this.fieldDescriptions = fieldDescriptions;
	}

	public Iterable<JAFieldDescription> findAvailableFilters() {
		List<JAFieldDescription> filterFields = Lists.newArrayList(filterAvailableFilters());
		Collections.sort(filterFields, new JAFieldDescriptionComparator());
		return filterFields;
	}
	
	public Iterable<JAFieldDescription> findFieldsToShow() {
		List<JAFieldDescription> fieldsToShow = Lists.newArrayList(filterFieldsToShow());
		Collections.sort(fieldsToShow, new JAFieldDescriptionComparator());
		return fieldsToShow;
	}
	
	public void validateInput(final Object newItem) {
		
		for (JAFieldDescription field : fieldDescriptions) {
			
			final Object value = JAReflections.getFieldValue(newItem, field.getName());
			
			validateRequired(field, value);

			validateMaxLength(field, value);

			validateRegex(field, value);
		}
	}

	public void validateInput(final Object oldItem, final Object newItem) {
		
		for (JAFieldDescription field : fieldDescriptions) {
			
			final Object newItemValue = JAReflections.getFieldValue(newItem, field.getName());
			
			validateRequired(field, newItemValue);
			
			validateEditable(field, oldItem, newItemValue);

			validateMaxLength(field, newItemValue);

			validateRegex(field, newItemValue);
		}
	}

	private Iterable<JAFieldDescription> filterAvailableFilters() {
		return Iterables.filter(fieldDescriptions, new Predicate<JAFieldDescription>() {
			public boolean apply(JAFieldDescription input) {
				return input.isFilter();
			}
		});
	}
	
	private Iterable<JAFieldDescription> filterFieldsToShow() {
		return Iterables.filter(fieldDescriptions, new Predicate<JAFieldDescription>() {
			public boolean apply(JAFieldDescription input) {
				return input.hasView();
			}
		});
	}
	
	private static void validateEditable(final JAFieldDescription field, final Object oldItem, final Object newItemvalue) {
		
		if (!field.isEditable()) {
			
			final Object oldItemvalue = JAReflections.getFieldValue(oldItem, field.getName());
			if (!ObjectUtils.equals(newItemvalue, oldItemvalue)) {
				
				final String errorMsg = String.format("Field '%s' is not editable", field.getName());
				throw new JARuntimeException(errorMsg);
			}
		}
	}
	
	private static void validateRequired(final JAFieldDescription field, final Object value) {
		
		if (field.isRequired() && value == null) {

			final String errorMsg = String.format("Value for field '%s' is required", field.getName());
			throw new JARuntimeException(errorMsg);
		}
	}

	private static void validateMaxLength(final JAFieldDescription field, final Object value) {

		final int maxLength = field.getMaxLength();
		if (maxLength != -1 && value != null && value.toString().length() > maxLength) {

			final String errorMsg = String.format("Value length for field '%s' cannot exceed %d characters : '%s'", field.getName(), maxLength, value);
			throw new JARuntimeException(errorMsg);
		}
	}

	private static void validateRegex(final JAFieldDescription field, final Object value) {

		final String regex = field.getRegularExpression();
		if (StringUtils.isNotBlank(regex) && value != null && !value.toString().matches(regex)) {

			final String errorMsg = String.format("Value for field '%s' did not match regex '%s' : '%s'", field.getName(), regex, value);
			throw new JARuntimeException(errorMsg);
		}
	}
}