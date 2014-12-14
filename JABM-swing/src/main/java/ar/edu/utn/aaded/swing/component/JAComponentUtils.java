package ar.edu.utn.aaded.swing.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.container.JAContainer;

public class JAComponentUtils {

	public static void removeErrorStatus(final JComponent component) {
		component.setToolTipText(null);
		component.setBorder(null);
		component.updateUI();
	}
	
	public static void setErrorStatus(final JComponent component, final Exception e) {
		component.setBorder(BorderFactory.createLineBorder(Color.red));
		component.setToolTipText(e.getMessage());
	}
	
	public static void addFieldLabel(final JAFieldDescription field, final JAContainer container) {
		
        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        container.addMember(fieldLabel);
	}
}
