package ar.edu.utn.aadeed.model;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.apache.commons.lang.StringUtils;

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
