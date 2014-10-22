package ar.edu.utn.aaded.swing.component;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAContainer;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.util.List;

public class JASelectItemComponent implements JAViewComponent {

	public JAViewType getViewType() {
        return JAViewType.SELECT_ITEM;
    }

	public void render(JAFieldDescription field, JAContainer container) {
    	
        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        container.addMember(fieldLabel);

		JComboBox jComboBox = new JComboBox(getEnumValues(field.getClazz()).toArray());
		jComboBox.setSelectedItem("");
		container.addMember(new JATextBoxMember(field, jComboBox));
    }

	public void render(Object object, JAFieldDescription field, JAContainer container) {

        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);

        String itemValue = JAReflections.getFieldValue(object, field.getName()).toString();
        container.addMember(fieldLabel);

		JComboBox jComboBox = new JComboBox(getEnumValues(field.getClazz()).toArray());
		jComboBox.setSelectedItem(itemValue);
		container.addMember(new JATextBoxMember(field, jComboBox));
    }

	private List<Object> getEnumValues(Class<?> clazz) {

		List<Object> values = Lists.newArrayList();

		values.add("");

		for (Object value : clazz.getEnumConstants()){
			values.add(value.toString());
		}

		return values;
	}
    
    public static class JATextBoxMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JComboBox textField;
    	
		public JATextBoxMember(JAFieldDescription field, JComboBox textField) {
			this.field = field;
			this.textField = textField;
		}

		public JAFieldDescription getField() {
			return field;
		}

		public Object getComponent() {
			return textField;
		}

		public Object getValue() {
			String value = textField.getSelectedItem().toString();
			return (StringUtils.isBlank(value)) ? null : value;
		}
    }
}
