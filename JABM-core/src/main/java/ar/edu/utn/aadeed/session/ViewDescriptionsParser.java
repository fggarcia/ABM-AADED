package ar.edu.utn.aadeed.session;

import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.edu.utn.aadeed.annotation.View;
import ar.edu.utn.aadeed.model.FieldDescription;
import ar.edu.utn.aadeed.model.ViewDescription;

import com.google.common.collect.Lists;

public class ViewDescriptionsParser {

	static final Logger Log = LoggerFactory.getLogger(ViewDescriptionsParser.class);

	public <T> List<ViewDescription> build(Class<T> clazz, List<FieldDescription> fieldDescriptions) {

		List<ViewDescription> result = Lists.newArrayList();
		
		for (FieldDescription fieldDescription : fieldDescriptions) {
			buildView(fieldDescription, clazz, result);
		}
		
		return result;
	}

	private <T> void buildView(FieldDescription fieldDescription, Class<T> clazz, List<ViewDescription> result) {

		try {
			
			Field field = clazz.getDeclaredField(fieldDescription.getName());
			field.setAccessible(true);
			if (field.isAnnotationPresent(View.class)) {
				
				View view = field.getAnnotation(View.class);
				
				ViewDescription viewDescription = new ViewDescription(fieldDescription);
				viewDescription.setLabel(view.label());
				viewDescription.setOrder(view.order());
				
				result.add(viewDescription);
			}
			
		} catch(Exception e) {
			String errorMsg = String.format("Could not retrieve View info from class %s", clazz.getName());
			Log.error(errorMsg, e);
			throw new RuntimeException(errorMsg);
		}
	}
}