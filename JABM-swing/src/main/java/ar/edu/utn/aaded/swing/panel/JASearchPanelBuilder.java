package ar.edu.utn.aaded.swing.panel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import ar.edu.utn.aadeed.builder.JAViewSearchPanelBuilder;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.panel.JAViewSearchPanel;

public class JASearchPanelBuilder implements JAViewSearchPanelBuilder {

	public JAViewSearchPanel build() {

		TitledBorder border = BorderFactory.createTitledBorder("Search fields");

		JPanel panel = new JPanel(new GridLayout(0, 4), false);
		panel.setBorder(border);

		Border margin = new EmptyBorder(10, 10, 10, 80);
		panel.setBorder(new CompoundBorder(border, margin));
		
		return new JASearchPanel(panel);
	}

	private static class JASearchPanel implements JAViewSearchPanel {

		private JPanel panel;
		
		public JASearchPanel(JPanel panel) {
			this.panel = panel;
		}

		public void render(JAViewContainer container) {
			container.addMember(Box.createRigidArea(new Dimension(0, 20)));
			container.addMember(panel);
		}

		public void addMember(Object member) {
			panel.add(JComponent.class.cast(member));
		}

		public void render() {
			panel.setVisible(true);
		}
	}
}
