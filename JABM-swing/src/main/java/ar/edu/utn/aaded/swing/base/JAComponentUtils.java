package ar.edu.utn.aaded.swing.base;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAMember;
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
	
	public static FocusListener createFocusListenerForUpdateAction(final JAFieldDescription field, final JAMember member, final JComponent component, final Object originalValue) {
		return new FocusListener() {
			
			public void focusLost(final FocusEvent fe) {
				final boolean check = !fe.isTemporary() && component.isEnabled();
				if (check) {
					try {
						field.validateInputToUpdate(originalValue, member.getValue());
						removeErrorStatus(component);
					} catch (Exception e) {
						setErrorStatus(component, e);
					}
				}
			}
			
			public void focusGained(FocusEvent e) {
				// Not needed
			}
		};
	}
	
	public static FocusListener createFocusListenerForAddAction(final JAFieldDescription field, final JAMember member, final JComponent component) {
		return new FocusListener() {
			
			public void focusLost(final FocusEvent fe) {
				final boolean check = !fe.isTemporary() && component.isEnabled();
				if (check) {
					try {
						field.validateInputToAdd(member.getValue());
						removeErrorStatus(component);
					} catch (Exception e) {
						setErrorStatus(component, e);
					}
				}
			}
			
			public void focusGained(FocusEvent e) {
				// Not needed
			}
		};
	}
}
