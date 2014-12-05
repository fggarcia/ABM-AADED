package ar.edu.utn.aadeed.session;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.exception.JAException;
import ar.edu.utn.aadeed.exception.JARuntimeException;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.comparator.JAFieldDescriptionComparator;
import ar.edu.utn.aadeed.validator.JAOperationValidator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class JAFields<T> {

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
	
	public void validateInputToAdd(final T newItem) {
		
		for (JAFieldDescription field : fieldDescriptions) {
			
			final Object value = JAReflections.getFieldValue(newItem, field.getName());
			
			validateRequired(field, value);

			validateMaxLength(field, value);

			validateRegex(field, value);
			
			tryValidatorsOnAdd(field, value);
		}
	}
	
	public void validateInputToDelete(final T oldItem) {
		
		for (JAFieldDescription field : fieldDescriptions) {
			
			final Object value = JAReflections.getFieldValue(oldItem, field.getName());
			
			tryValidatorsOnDelete(field, value);
		}
	}

	public void validateInputToUpdate(final T oldItem, final T newItem) {
		
		for (JAFieldDescription field : fieldDescriptions) {
			
			final Object newItemValue = JAReflections.getFieldValue(newItem, field.getName());
			
			final Object oldItemValue = JAReflections.getFieldValue(oldItem, field.getName());
			
			validateRequired(field, newItemValue);
			
			validateEditable(field, oldItemValue, newItemValue);

			validateMaxLength(field, newItemValue);

			validateRegex(field, newItemValue);
			
			tryValidatorsOnUpdate(field, oldItemValue, newItemValue);
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
	
	private void validateEditable(final JAFieldDescription field, final Object oldItemvalue, final Object newItemvalue) {
		
		if (!field.isEditable()) {
			
			if (!ObjectUtils.equals(newItemvalue, oldItemvalue)) {
				
				final String errorMsg = String.format("Field '%s' is not editable", field.getName());
				throw new JARuntimeException(errorMsg);
			}
		}
	}
	
	private void validateRequired(final JAFieldDescription field, final Object value) {
		
		if (field.isRequired() && value == null) {

			final String errorMsg = String.format("Value for field '%s' is required", field.getName());
			throw new JARuntimeException(errorMsg);
		}
	}

	private void validateMaxLength(final JAFieldDescription field, final Object value) {

		final int maxLength = field.getMaxLength();
		if (maxLength != -1 && value != null && value.toString().length() > maxLength) {

			final String errorMsg = String.format("Value length for field '%s' cannot exceed %d characters : '%s'", field.getName(), maxLength, value);
			throw new JARuntimeException(errorMsg);
		}
	}

	private void validateRegex(final JAFieldDescription field, final Object value) {

		final String regex = field.getRegularExpression();
		if (StringUtils.isNotBlank(regex) && value != null && !value.toString().matches(regex)) {

			final String errorMsg = String.format("Value for field '%s' did not match regex '%s' : '%s'", field.getName(), regex, value);
			throw new JARuntimeException(errorMsg);
		}
	}
	
	private void tryValidatorsOnDelete(final JAFieldDescription field, final Object value) {
		
		for (JAOperationValidator validator : field.getValidators()) {
			try {
				validator.validateOnDelete(value);
			} catch (JAException e) {
				throw new JARuntimeException(e.getMessage());
			}
		}
	}
	
	private void tryValidatorsOnAdd(final JAFieldDescription field, final Object value) {
		
		for (JAOperationValidator validator : field.getValidators()) {
			try {
				validator.validateOnAdd(value);
			} catch (JAException e) {
				throw new JARuntimeException(e.getMessage());
			}
		}
	}
	
	private void tryValidatorsOnUpdate(final JAFieldDescription field, final Object oldValue, final Object newValue) {
		
		for (JAOperationValidator validator : field.getValidators()) {
			try {
				validator.validateOnUpdate(oldValue, newValue);
			} catch (JAException e) {
				throw new JARuntimeException(e.getMessage());
			}
		}
	}
}