package ar.edu.utn.aaded.swing.component;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAContainer;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.apache.commons.lang.StringUtils;

import javax.swing.*;

import java.util.List;

public class JASelectItemComponent implements JAViewComponent {
	
	private static final String EMPTY_OPTION = "";

	public JAViewType getViewType() {
        return JAViewType.SELECT_ITEM;
    }

	public void renderForSearch(JAFieldDescription field, JAContainer container) {
    	
        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        container.addMember(fieldLabel);

		JComboBox<String> jComboBox = createComboBox(field);
		jComboBox.setSelectedItem(EMPTY_OPTION);
		container.addMember(new JASelectItemMember(field, jComboBox));
    }

	public void renderForAdd(JAFieldDescription field, JAContainer container) {
		this.renderForSearch(field, container);
	}

	public void renderForUpdate(Object object, JAFieldDescription field, JAContainer container) {

        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        container.addMember(fieldLabel);
        
        Object itemValue = JAReflections.getFieldValue(object, field.getName());
        String stringItemValue = MoreObjects.firstNonNull(itemValue, EMPTY_OPTION).toString();

        JComboBox<String> jComboBox = createComboBox(field);
		jComboBox.setSelectedItem(stringItemValue);
		jComboBox.setEnabled(field.isEditable());
		
		container.addMember(new JASelectItemMember(field, jComboBox));
    }

	private JComboBox<String> createComboBox(JAFieldDescription field) {
		
		List<Object> values = getEnumValues(field.getClazz());
		
		return new JComboBox<String>(values.toArray(new String[values.size()]));
	}
	
	private List<Object> getEnumValues(Class<?> clazz) {

		List<Object> values = Lists.newArrayList();

		values.add(EMPTY_OPTION);

		for (Object value : clazz.getEnumConstants()){
			values.add(value.toString());
		}

		return values;
	}
    
    public static class JASelectItemMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JComboBox<String> comboBox;
    	
		public JASelectItemMember(JAFieldDescription field, JComboBox<String> comboBox) {
			this.field = field;
			this.comboBox = comboBox;
		}

		public JAFieldDescription getField() {
			return field;
		}

		public Object getComponent() {
			return comboBox;
		}

		public Object getValue() {
			String value = comboBox.getSelectedItem().toString();
			return (StringUtils.isBlank(value)) ? null : findEnum(value);
		}
		
		private Object findEnum(final String value) {
			
			Object[] enums = field.getClazz().getEnumConstants();
			
			return Iterables.find(Lists.newArrayList(enums), new Predicate<Object>() {
				public boolean apply(Object input) {
					return Enum.class.cast(input).name().equals(value);
				}
			});
		}
    }
}
