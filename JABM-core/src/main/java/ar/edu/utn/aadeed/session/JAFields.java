package ar.edu.utn.aadeed.session;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.List;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.comparator.JAFieldDescriptionComparator;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

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
			
		}
	}

	public void validateInput(final Object oldItem, final Object newItem) {
		this.validateInput(newItem);
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
	
	private static void validateRequired(final JAFieldDescription field, final Object value) {
		
		if (field.isRequired() && value == null) {
			
		}
	}
}