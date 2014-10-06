package ar.edu.utn.aaded.swing;

import java.awt.Component;

import javax.swing.JTextField;

public class JAComponentUtils {

	public static String getValue(Component component) {
		if (JTextField.class.isInstance(component)) {
			return JTextField.class.cast(component).getText();
		}
		return "";
	}
}
