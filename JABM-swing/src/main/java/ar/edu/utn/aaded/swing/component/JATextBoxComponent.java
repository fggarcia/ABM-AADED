package ar.edu.utn.aaded.swing.component;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

import javax.swing.*;

public class JATextBoxComponent implements JAViewComponent {

	public JAViewType getViewType() {
        return JAViewType.TEXT_BOX;
    }

    public void render(JAFieldDescription field, JAViewContainer container) {
    	
        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        JTextField textField = new JTextField(15);
        textField.setName(field.getName());
        
        container.addMember(fieldLabel);
        container.addMember(textField);
    }

    public void render(Object object, JAFieldDescription field, JAViewContainer container) {

    }
}
