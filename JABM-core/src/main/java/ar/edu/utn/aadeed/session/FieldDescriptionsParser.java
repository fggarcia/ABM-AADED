package ar.edu.utn.aadeed.session;

import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.annotation.Descriptor;
import ar.edu.utn.aadeed.annotation.View;
import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.model.ViewDescription;

import com.google.common.collect.Lists;

public class FieldDescriptionsParser {

	static final Logger Log = LoggerFactory.getLogger(FieldDescriptionsParser.class);

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
			
			ViewDescription viewDescription = buildView(field);
			if (viewDescription == null) {
				throw new IllegalArgumentException(String.format("Field %s must be annotated with View", field.getName()));
			}
			
			FieldDescription fieldDescription = new FieldDescription(field.getName(), field.getType(), viewDescription);
			fieldDescription.setEditable(descriptor.editable());
			fieldDescription.setFilter(descriptor.filter());
			fieldDescription.setRequired(descriptor.required());

			result.add(fieldDescription);
		}
	}
	
	private ViewDescription buildView(Field field) {

		ViewDescription viewDescription = null;
		try {
			
			if (field.isAnnotationPresent(View.class)) {
				
				View view = field.getAnnotation(View.class);
				viewDescription = new ViewDescription(view.component());
				viewDescription.setLabel(view.label());
				viewDescription.setOrder(view.order());
				viewDescription.setSize(view.size());
			}
			
		} catch(Exception e) {
			String errorMsg = String.format("Could not retrieve View info from field %s", field.getName());
			Log.error(errorMsg, e);
			throw new RuntimeException(errorMsg);
		}
		
		return viewDescription;
	}
}