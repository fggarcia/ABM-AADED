package ar.edu.utn.aadeed.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import ar.edu.utn.aadeed.exception.JAException;
import ar.edu.utn.aadeed.exception.JARuntimeException;
import ar.edu.utn.aadeed.validator.JAOperationValidator;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

public class JAFieldDescription {

	private String name;

	private Class<?> clazz;

	private JAViewDescription view;

	private boolean required;

	private boolean editable;

	private boolean filter;

	private int maxLength;

	private String regularExpression;
	
	private List<JAOperationValidator> validators = Lists.newArrayList();

	public JAFieldDescription(String name, Class<?> clazz, JAViewDescription view) {
		this.name = name;
		this.clazz = clazz;
		this.view = view;
	}

	public boolean hasView() {
		return view != null;
	}

	public String getLabel() {

		if (hasView()) {

			String viewLabel = view.getLabel();
			if (!Strings.isNullOrEmpty(viewLabel)) {
				return StringUtils.capitalize(viewLabel);
			}
		}

		return StringUtils.capitalize(name);
	}
	
	public void addValidator(final JAOperationValidator validator) {
		checkArgument(validator != null, "validator cannot be null");
		this.validators.add(validator);
	}
	
	public void validateInputToAdd(final Object value) {
		
		validateRequired(value);

		validateMaxLength(value);

		validateRegex(value);
			
		tryValidatorsOnAdd(value);
	}
	
	public void validateInputToDelete(final Object value) {
		
		tryValidatorsOnDelete(value);
	}
	
	public void validateInputToUpdate(final Object oldItemValue, final Object newItemValue) {
		
		validateRequired(newItemValue);
			
		validateEditable(oldItemValue, newItemValue);

		validateMaxLength(newItemValue);

		validateRegex(newItemValue);
			
		tryValidatorsOnUpdate(oldItemValue, newItemValue);
	}
	
	private void validateEditable(final Object oldItemvalue, final Object newItemvalue) {
		
		if (!isEditable()) {
			
			if (!ObjectUtils.equals(newItemvalue, oldItemvalue)) {
				
				final String errorMsg = String.format("Field '%s' is not editable", getName());
				throw new JARuntimeException(errorMsg);
			}
		}
	}
	
	private void validateRequired(final Object value) {
		
		if (isRequired() && value == null) {

			final String errorMsg = String.format("Value for field '%s' is required", getName());
			throw new JARuntimeException(errorMsg);
		}
	}

	private void validateMaxLength(final Object value) {

		final int maxLength = getMaxLength();
		if (maxLength != -1 && value != null && value.toString().length() > maxLength) {

			final String errorMsg = String.format("Value length for field '%s' cannot exceed %d characters : '%s'", getName(), maxLength, value);
			throw new JARuntimeException(errorMsg);
		}
	}

	private void validateRegex(final Object value) {

		final String regex = getRegularExpression();
		if (StringUtils.isNotBlank(regex) && value != null && !value.toString().matches(regex)) {

			final String errorMsg = String.format("Value for field '%s' did not match regex '%s' : '%s'", getName(), regex, value);
			throw new JARuntimeException(errorMsg);
		}
	}
	
	private void tryValidatorsOnDelete(final Object value) {
		
		for (JAOperationValidator validator : getValidators()) {
			try {
				validator.validateOnDelete(value);
			} catch (JAException e) {
				throw new JARuntimeException(e.getMessage());
			}
		}
	}
	
	private void tryValidatorsOnAdd(final Object value) {
		
		for (JAOperationValidator validator : getValidators()) {
			try {
				validator.validateOnAdd(value);
			} catch (JAException e) {
				throw new JARuntimeException(e.getMessage());
			}
		}
	}
	
	private void tryValidatorsOnUpdate(final Object oldValue, final Object newValue) {
		
		for (JAOperationValidator validator : getValidators()) {
			try {
				validator.validateOnUpdate(oldValue, newValue);
			} catch (JAException e) {
				throw new JARuntimeException(e.getMessage());
			}
		}
	}
	
	public List<JAOperationValidator> getValidators() {
		return validators;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isFilter() {
		return filter;
	}

	public void setFilter(boolean filter) {
		this.filter = filter;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public JAViewDescription getView() {
		return view;
	}

	public int getMaxLength() {
		return maxLength;
	}
	
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public String getRegularExpression() {
		return regularExpression;
	}

	public void setRegularExpression(String regularExpression) {
		this.regularExpression = regularExpression;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("name", name).add("required", required)
				.add("editable", editable).add("filter", filter).toString();
	}
}
