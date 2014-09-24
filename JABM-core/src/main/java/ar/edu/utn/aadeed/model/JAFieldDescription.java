package ar.edu.utn.aadeed.model;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Strings;

public class JAFieldDescription {

	private String name;
	
	private Class<?> clazz;
	
	private JAViewDescription view;
	
	private boolean required;

	private boolean editable;

	private boolean filter;

	public JAFieldDescription(String name, Class<?> clazz, JAViewDescription view) {
		this.name = name;
		this.clazz = clazz;
		this.view = view;
	}
	
	public String getLabel() {
		String viewLabel = view.getLabel();
		if (!Strings.isNullOrEmpty(viewLabel)) {
			return StringUtils.capitalize(viewLabel);
		}
		return StringUtils.capitalize(name);
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
}