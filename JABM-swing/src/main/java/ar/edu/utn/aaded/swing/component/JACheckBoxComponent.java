package ar.edu.utn.aaded.swing.component;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAContainer;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.util.List;

public class JACheckBoxComponent implements JAViewComponent {
	
	private static final String EMPTY_OPTION = "";

	public JAViewType getViewType() {
        return JAViewType.CHECK_BOX;
    }

	public void renderForSearch(JAFieldDescription field, JAContainer container) {
    	
        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        container.addMember(fieldLabel);

		JComboBox<String> jComboBox = new JComboBox<String>();
		jComboBox.addItem(EMPTY_OPTION);
		jComboBox.setSelectedItem(EMPTY_OPTION);
		addCheckBoxOptions(jComboBox);
		container.addMember(new JAComboBoxMember(field, jComboBox));
    }

	public void renderForAdd(JAFieldDescription field, JAContainer container) {

		JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
		container.addMember(fieldLabel);

		JCheckBox jCheckBox = new JCheckBox();
		container.addMember(new JACheckBoxMember(field, jCheckBox));
	}

	public void renderForUpdate(Object object, JAFieldDescription field, JAContainer container) {

        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        container.addMember(fieldLabel);
        
        Object itemValue = JAReflections.getFieldValue(object, field.getName());
        String stringItemValue = MoreObjects.firstNonNull(itemValue, EMPTY_OPTION).toString();

		JCheckBox jCheckBox = new JCheckBox();

		jCheckBox.setSelected(Boolean.valueOf(stringItemValue));

		container.addMember(new JACheckBoxMember(field, jCheckBox));
    }

	private void addCheckBoxOptions(JComboBox<String> jComboBox) {
		for (Object option : getBooleanValues()){
			jComboBox.addItem(option.toString());
		}
	}
	
	private List<Object> getBooleanValues() {

		List<Object> values = Lists.newArrayList();

		values.add(Boolean.TRUE.toString());
		values.add(Boolean.FALSE.toString());

		return values;
	}
    
    public static class JAComboBoxMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JComboBox<String> comboBox;
    	
		public JAComboBoxMember(JAFieldDescription field, JComboBox<String> comboBox) {
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
			return (StringUtils.isBlank(value)) ? null : findBoolean(value);
		}
		
		private Boolean findBoolean(final String value) {
			return Boolean.valueOf(value);
		}
    }

	public static class JACheckBoxMember implements JAMember {

		private JAFieldDescription field;

		private JCheckBox checkbox;

		public JACheckBoxMember(JAFieldDescription field, JCheckBox checkbox) {
			this.field = field;
			this.checkbox = checkbox;
		}

		public JAFieldDescription getField() {
			return field;
		}

		public Object getComponent() {
			return checkbox;
		}

		public Object getValue() {
			return checkbox.isSelected();
		}
	}
}