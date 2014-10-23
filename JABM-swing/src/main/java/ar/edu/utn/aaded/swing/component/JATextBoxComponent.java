package ar.edu.utn.aaded.swing.component;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;

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

	public void renderForSearch(JAFieldDescription field, JAContainer container) {
    	
        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        JTextField textField = new JTextField(15);
        
        container.addMember(fieldLabel);
        container.addMember(new JATextBoxMember(field, textField));
    }

	public void renderForAdd(JAFieldDescription field, JAContainer container) {
		this.renderForSearch(field, container);
	}

	public void renderForUpdate(Object object, JAFieldDescription field, JAContainer container) {

        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        JTextField textField = new JTextField(15);
        
        Object itemValue = JAReflections.getFieldValue(object, field.getName());
        String stringItemValue = MoreObjects.firstNonNull(itemValue, "").toString();
        
        textField.setText(stringItemValue);
        
        container.addMember(fieldLabel);
        container.addMember(new JATextBoxMember(field, textField));
    }
    
    public static class JATextBoxMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JTextField textField;
    	
		public JATextBoxMember(JAFieldDescription field, JTextField textField) {
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
