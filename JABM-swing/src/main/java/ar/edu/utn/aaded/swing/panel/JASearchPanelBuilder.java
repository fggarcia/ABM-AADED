package ar.edu.utn.aaded.swing.panel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import ar.edu.utn.aadeed.builder.JAViewSearchPanelBuilder;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.panel.JAViewSearchPanel;

public class JASearchPanelBuilder implements JAViewSearchPanelBuilder {

	public <T> JAViewSearchPanel<T> build(JAMainPagePanel<T> mainPagePanel) {
		return new JASearchPanel<T>(mainPagePanel);
	}

	private static class JASearchPanel<T> implements JAViewSearchPanel<T> {

		private JPanel containerPanel;
		private JPanel fieldsPanel;
		private JAMainPagePanel<T> mainPagePanel;

		public JASearchPanel(JAMainPagePanel<T> mainPagePanel) {
		
			this.mainPagePanel = mainPagePanel;
			this.containerPanel = createContainerPanel();
			this.fieldsPanel = createFieldsPanel();
			
			containerPanel.add(fieldsPanel);
			containerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
			containerPanel.add(new JButton("Search"));
		}

		public void render(JAViewContainer container) {
			container.addMember(Box.createRigidArea(new Dimension(0, 20)));
			container.addMember(containerPanel);
		}

		public void addMember(Object member) {
			fieldsPanel.add(JComponent.class.cast(member));
		}

		public void render() {
			containerPanel.setVisible(true);
		}
		
		private JPanel createContainerPanel() {
			
			JPanel panel = new JPanel();
			BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
			panel.setLayout(layout);

			Border margin = new EmptyBorder(10, 10, 10, 80);
			TitledBorder border = BorderFactory.createTitledBorder("Search fields");
			panel.setBorder(new CompoundBorder(border, margin));
			
			return panel;
		}
		
		private JPanel createFieldsPanel() {
			return new JPanel(new GridLayout(0, 4), false);
		}
	}
}
