package ar.edu.utn.aaded.swing.component;

import java.util.List;

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
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class JASelectItemComponent implements JAViewComponent {
	
	private static final String EMPTY_OPTION = "";

	public JAViewType getViewType() {
        return JAViewType.SELECT_ITEM;
    }

	public void renderForSearch(JAFieldDescription field, JAContainer container) {

		JAComponentUtils.addFieldLabel(field, container);

		JComboBox<String> jComboBox = createComboBox(field);
		jComboBox.setSelectedItem(EMPTY_OPTION);
		
		container.addMember(JASelectItemMember.getInstanceForSearchAction(field, jComboBox));
    }

	public void renderForAdd(JAFieldDescription field, JAContainer container) {
		
		JAComponentUtils.addFieldLabel(field, container);

		JComboBox<String> jComboBox = createComboBox(field);
		jComboBox.setSelectedItem(EMPTY_OPTION);
		
		container.addMember(JASelectItemMember.getInstanceForAddAction(field, jComboBox));
	}

	public void renderForUpdate(Object object, JAFieldDescription field, JAContainer container) {

		JAComponentUtils.addFieldLabel(field, container);
		
        Object itemValue = JAReflections.getFieldValue(object, field.getName());
        String stringItemValue = MoreObjects.firstNonNull(itemValue, EMPTY_OPTION).toString();

        JComboBox<String> jComboBox = createComboBox(field);
		jComboBox.setSelectedItem(stringItemValue);
		jComboBox.setEnabled(field.isEditable());
		
		container.addMember(JASelectItemMember.getInstanceForUpdateAction(field, jComboBox, itemValue));
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
    
    private static class JASelectItemMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JComboBox<String> comboBox;
    	
    	public static JASelectItemMember getInstanceForSearchAction(final JAFieldDescription field, final JComboBox<String> comboBox) {
    		return new JASelectItemMember(field, comboBox);
    	}
    	
    	public static JASelectItemMember getInstanceForUpdateAction(final JAFieldDescription field, final JComboBox<String> comboBox, final Object originalValue) {
    		
    		final JASelectItemMember selectItemMember = new JASelectItemMember(field, comboBox);
    		
    		comboBox.addFocusListener(JAComponentUtils.createFocusListenerForUpdateAction(field, selectItemMember, comboBox, originalValue));
    		
    		return selectItemMember;
    	}
    	
    	public static JASelectItemMember getInstanceForAddAction(final JAFieldDescription field, final JComboBox<String> comboBox) {
    		
    		final JASelectItemMember selectItemMember = new JASelectItemMember(field, comboBox);
    		
    		comboBox.addFocusListener(JAComponentUtils.createFocusListenerForAddAction(field, selectItemMember, comboBox));
    		
    		return selectItemMember;
    	}
    	
    	private JASelectItemMember(JAFieldDescription field, JComboBox<String> comboBox) {
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
