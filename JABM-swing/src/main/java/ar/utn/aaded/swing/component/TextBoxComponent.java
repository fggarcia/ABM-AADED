package ar.utn.aaded.swing.component;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

import javax.swing.*;

public class TextBoxComponent implements JAViewComponent {
    @Override
    public JAViewType getViewType() {
        return JAViewType.TEXT_BOX;
    }

    @Override
    public void render(JAFieldDescription field, JAViewContainer container) {
        JLabel fieldLabel = new JLabel(field.getLabel());

        JTextField textField = new JTextField(15);

        container.addMember(fieldLabel);
        container.addMember(textField);
    }

    @Override
    public void render(Object object, JAFieldDescription field, JAViewContainer container) {

    }
}