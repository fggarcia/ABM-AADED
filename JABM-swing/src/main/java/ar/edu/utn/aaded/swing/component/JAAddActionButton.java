package ar.edu.utn.aaded.swing.component;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.edu.utn.aadeed.view.component.JAViewActionButton;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.table.JAViewRecordTable;

public class JAAddActionButton implements JAViewActionButton {

	public <T> void render(JAViewContainer container,
			final JAViewRecordTable<T> table) {
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String[] items = { "One", "Two", "Three", "Four", "Five" };
				JComboBox combo = new JComboBox(items);
				JTextField field1 = new JTextField("1234.56");
				JTextField field2 = new JTextField("9876.54");
				JPanel panel = new JPanel(new GridLayout(0, 1));
				panel.add(combo);
				panel.add(new JLabel("Field 1:"));
				panel.add(field1);
				panel.add(new JLabel("Field 2:"));
				panel.add(field2);
				JOptionPane
						.showConfirmDialog(null, panel, "Test",
								JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE);
			}
		});
		container.addMember(add);
	}
}