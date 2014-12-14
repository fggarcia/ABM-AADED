package ar.edu.utn.aaded.swing.component;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;

import ar.edu.utn.aaded.swing.base.JACustomTextField;
import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAContainer;

import com.google.common.base.MoreObjects;

public class JATextBoxComponent implements JAViewComponent {
	
	public JAViewType getViewType() {
        return JAViewType.TEXT_BOX;
    }

	public void renderForSearch(final JAFieldDescription field, final JAContainer container) {
		JAComponentUtils.addFieldLabel(field, container);
        container.addMember(JATextBoxMember.getInstanceForSearchAction(field, new JACustomTextField(field, 15)));
    }

	public void renderForAdd(final JAFieldDescription field, final JAContainer container) {
		JAComponentUtils.addFieldLabel(field, container);
        container.addMember(JATextBoxMember.getInstanceForAddAction(field, new JACustomTextField(field, 15)));
	}

	public void renderForUpdate(final Object object, final JAFieldDescription field, final JAContainer container) {
		
		JAComponentUtils.addFieldLabel(field, container);

        JTextField textField = new JACustomTextField(field, 15);
        
        Object itemValue = JAReflections.getFieldValue(object, field.getName());
        String stringItemValue = MoreObjects.firstNonNull(itemValue, "").toString();
        
        textField.setText(stringItemValue);
		textField.setEnabled(field.isEditable());
        
        container.addMember(JATextBoxMember.getInstanceForUpdateAction(field, textField, itemValue));
    }
	
    private static class JATextBoxMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JTextField textField;
    	
    	public static JATextBoxMember getInstanceForSearchAction(final JAFieldDescription field, final JTextField textField) {
    		return new JATextBoxMember(field, textField);
    	}
    	
    	public static JATextBoxMember getInstanceForUpdateAction(final JAFieldDescription field, final JTextField textField, final Object originalValue) {
    		
    		final JATextBoxMember textBoxMember = new JATextBoxMember(field, textField);
    		
    		textField.addFocusListener(new FocusListener() {
				
				public void focusGained(final FocusEvent fe) {
					// Not needed
				}
				
				public void focusLost(final FocusEvent fe) {
					final boolean check = !fe.isTemporary() && textField.isEnabled() && textField.isEditable();
					if (check) {
						try {
							field.validateInputToUpdate(originalValue, textBoxMember.getValue());
							JAComponentUtils.removeErrorStatus(textField);
						} catch (Exception e) {
							JAComponentUtils.setErrorStatus(textField, e);
						}
					}
				}
			});
    		
    		return textBoxMember;
    	}
    	
    	public static JATextBoxMember getInstanceForAddAction(final JAFieldDescription field, final JTextField textField) {
    		
    		final JATextBoxMember textBoxMember = new JATextBoxMember(field, textField);
    		
    		textField.addFocusListener(new FocusListener() {
				
				public void focusGained(final FocusEvent fe) {
					// Not needed
				}
				
				public void focusLost(final FocusEvent fe) {
					final boolean check = !fe.isTemporary() && textField.isEnabled() && textField.isEditable();
					if (check) {
						try {
							field.validateInputToAdd(textBoxMember.getValue());
							JAComponentUtils.removeErrorStatus(textField);
						} catch (Exception e) {
							JAComponentUtils.setErrorStatus(textField, e);
						}
					}
				}
			});
    		
    		return textBoxMember;
    	}
    	
		private JATextBoxMember(final JAFieldDescription field, final JTextField textField) {
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
			String value = textField.getText();
			return (StringUtils.isBlank(value)) ? null : ConvertUtils.convert(value, field.getClazz());
		}
    }
}