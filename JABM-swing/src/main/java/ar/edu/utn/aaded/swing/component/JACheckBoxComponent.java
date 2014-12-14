package ar.edu.utn.aaded.swing.component;

import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import org.apache.commons.lang.StringUtils;

import ar.edu.utn.aaded.swing.base.JAComponentUtils;
import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAContainer;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

public class JACheckBoxComponent implements JAViewComponent {
	
	private static final String EMPTY_OPTION = "";

	public JAViewType getViewType() {
        return JAViewType.CHECK_BOX;
    }

	public void renderForSearch(JAFieldDescription field, JAContainer container) {
    	
		JAComponentUtils.addFieldLabel(field, container);
		
		JComboBox<String> jComboBox = new JComboBox<String>();
		jComboBox.addItem(EMPTY_OPTION);
		jComboBox.setSelectedItem(EMPTY_OPTION);
		addCheckBoxOptions(jComboBox);
		
		container.addMember(JAComboBoxMember.getInstanceForSearchAction(field, jComboBox));
    }

	public void renderForAdd(JAFieldDescription field, JAContainer container) {

		JAComponentUtils.addFieldLabel(field, container);
		
		JCheckBox jCheckBox = new JCheckBox();
		container.addMember(JACheckBoxMember.getInstanceForAddAction(field, jCheckBox));
	}

	public void renderForUpdate(Object object, JAFieldDescription field, JAContainer container) {

		JAComponentUtils.addFieldLabel(field, container);
		
        Object itemValue = JAReflections.getFieldValue(object, field.getName());
        String stringItemValue = MoreObjects.firstNonNull(itemValue, EMPTY_OPTION).toString();

		JCheckBox jCheckBox = new JCheckBox();

		jCheckBox.setSelected(Boolean.valueOf(stringItemValue));
		jCheckBox.setEnabled(field.isEditable());

		container.addMember(JACheckBoxMember.getInstanceForUpdateAction(field, jCheckBox, itemValue));
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
    
    private static class JAComboBoxMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JComboBox<String> comboBox;
    	
    	public static JAComboBoxMember getInstanceForSearchAction(final JAFieldDescription field, final JComboBox<String> jComboBox) {
    		return new JAComboBoxMember(field, jComboBox);
    	}
    	
    	private JAComboBoxMember(JAFieldDescription field, JComboBox<String> comboBox) {
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

	private static class JACheckBoxMember implements JAMember {

		private JAFieldDescription field;

		private JCheckBox checkbox;
		
    	public static JACheckBoxMember getInstanceForAddAction(final JAFieldDescription field, final JCheckBox jCheckBox) {
    		
    		final JACheckBoxMember checkBoxMember = new JACheckBoxMember(field, jCheckBox);
    		
    		jCheckBox.addFocusListener(JAComponentUtils.createFocusListenerForAddAction(field, checkBoxMember, jCheckBox));
    		
    		return checkBoxMember;
    	}
		
		private static JACheckBoxMember getInstanceForUpdateAction(final JAFieldDescription field, final JCheckBox jCheckBox, final Object originalValue) {
    		
    		final JACheckBoxMember checkBoxMember = new JACheckBoxMember(field, jCheckBox);
    		
    		jCheckBox.addFocusListener(JAComponentUtils.createFocusListenerForUpdateAction(field, checkBoxMember, jCheckBox, originalValue));
    		
    		return checkBoxMember;
    	}

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