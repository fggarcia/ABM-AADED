package ar.edu.utn.aaded.swing.container;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.container.builder.JAViewContainerBuilder;

public class JAPopupContainerBuilder implements JAViewContainerBuilder {

	public JAViewContainer build() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 4));

		return new JAPopupContainer(panel);
	}

	private static class JAPopupContainer implements JAViewContainer {

		private JPanel panel;

		public JAPopupContainer(JPanel panel) {
			this.panel = panel;
		}

		public void addMember(Object member) {
			panel.add(JComponent.class.cast(member));
		}

		public void render() {
			JOptionPane.showConfirmDialog(null, panel, "Add item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		}
	}
}
