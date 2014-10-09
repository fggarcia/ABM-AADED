package ar.edu.utn.aaded.swing.panel;

import ar.edu.utn.aaded.swing.JAComponentUtils;
import ar.edu.utn.aadeed.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.builder.JAViewSearchPanelBuilder;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.panel.JAViewSearchPanel;
import com.google.common.base.Strings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
			
			this.containerPanel.add(this.fieldsPanel);
			this.containerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		}

		public void render(JAViewContainer container) {
			container.addMember(Box.createRigidArea(new Dimension(0, 20)));
			container.addMember(containerPanel);
		}

		public void addMember(Object member) {
			JComponent component = JComponent.class.cast(member);
			component.addKeyListener(createKeyListener());
			fieldsPanel.add(component);
		}

		public void render() {
			containerPanel.setVisible(true);
		}

		private KeyListener createKeyListener() {
			return new KeyListener() {
				
				public void keyTyped(KeyEvent arg0) {
				}
				
				public void keyReleased(KeyEvent arg0) {
					
					JAFiltersBuilder<T> filtersBuilder = mainPagePanel.getFiltersBuilder();
					
					for (Component component : fieldsPanel.getComponents()) {

						String name = component.getName();
						if (filtersBuilder.isValidSearchField(name)) {
							
							String value = JAComponentUtils.getValue(component);
							if(!Strings.isNullOrEmpty(value)) {
								filtersBuilder.add(name, value);
							}
						}
					}

					mainPagePanel.refreshTable(filtersBuilder.search());
				}
				
				public void keyPressed(KeyEvent arg0) {
				}
			};
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
