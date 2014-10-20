package ar.edu.utn.aaded.swing.component;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAContainer;

public class JATextBoxComponent implements JAViewComponent {

	public JAViewType getViewType() {
        return JAViewType.TEXT_BOX;
    }

	public void render(JAFieldDescription field, JAContainer container) {
    	
        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        JTextField textField = new JTextField(15);
        
        container.addMember(fieldLabel);
        container.addMember(new JATextBoxMember(field, textField));
    }

	public void render(Object object, JAFieldDescription field, JAContainer container) {

        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        JTextField textField = new JTextField(15);
        
        String itemValue = JAReflections.getFieldValue(object, field.getName()).toString();
        textField.setText(itemValue);
        
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
			return (StringUtils.isBlank(value)) ? null : value;
		}
    }
}
