package ar.edu.utn.aaded.swing.panel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.panel.JAViewSearchPanel;

public class JASearchPanel implements JAViewSearchPanel {

	public void render(JAViewContainer container) {

		TitledBorder titled;

		titled = BorderFactory.createTitledBorder("Search fields");
		addCompForBorder(titled, container);

	}

	void addCompForBorder(Border border, JAViewContainer container) {
		JPanel comp = new JPanel(new GridLayout(0, 4), false);
		comp.setBorder(border);

		comp.add(new JTextField(15));
		comp.add(new JTextField(15));
		comp.add(new JTextField(15));
		comp.add(new JTextField(15));

		Border margin = new EmptyBorder(10, 10, 10, 10);
		comp.setBorder(new CompoundBorder(border, margin));

		container.addMember(Box.createRigidArea(new Dimension(0, 20)));
		container.addMember(comp);
	}
}
