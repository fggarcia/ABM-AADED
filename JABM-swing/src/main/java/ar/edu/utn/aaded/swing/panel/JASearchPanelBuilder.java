package ar.edu.utn.aaded.swing.panel;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import ar.edu.utn.aadeed.session.builder.JAFiltersBuilder;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.panel.JAViewSearchPanel;
import ar.edu.utn.aadeed.view.panel.builder.JAViewSearchPanelBuilder;

import com.google.common.collect.Lists;
import org.jdesktop.swingx.JXDatePicker;

public class JASearchPanelBuilder implements JAViewSearchPanelBuilder {

	public <T> JAViewSearchPanel<T> build(JAMainPagePanel<T> mainPagePanel) {
		return new JASearchPanel<T>(mainPagePanel);
	}

	private static class JASearchPanel<T> implements JAViewSearchPanel<T> {

		private JPanel containerPanel;
		
		private JPanel fieldsPanel;
		
		private List<JAMember> members = Lists.newArrayList();
		
		private JAMainPagePanel<T> mainPagePanel;
		
		public JASearchPanel(JAMainPagePanel<T> mainPagePanel) {
		
			this.mainPagePanel = mainPagePanel;
			this.containerPanel = createContainerPanel();
			this.fieldsPanel = createFieldsPanel();
			
			this.containerPanel.add(this.fieldsPanel);
			this.containerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		}

		public void renderIn(JAViewContainer<T> container) {
			container.addMember(Box.createRigidArea(new Dimension(0, 20)));
			container.addMember(containerPanel);
		}

		public void addMember(JAMember member) {
			
			JComponent component = JComponent.class.cast(member.getComponent());

			if (component instanceof ItemSelectable){
				ItemSelectable.class.cast(component).addItemListener(createItemChangeLister());
			}
			else if (component instanceof JXDatePicker){
				JXDatePicker.class.cast(component)
						.addPropertyChangeListener(createPropertyChangeListener());
			}
			else{
				component.addKeyListener(createKeyListener());
			}

			members.add(member);
			fieldsPanel.add(component);
		}
		
		public void addMember(Object member) {
			JComponent component = JComponent.class.cast(member);
			fieldsPanel.add(component);
		}
		
		public JAFiltersBuilder<T> getFiltersBuilder() {
			
			JAFiltersBuilder<T> filtersBuilder = mainPagePanel.getFiltersBuilder();
			
			for (JAMember member : members) {

				String name = member.getField().getName();
				filtersBuilder.add(name, member.getValue());
			}
			
			return filtersBuilder;
		}

		private PropertyChangeListener createPropertyChangeListener() {
			return new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					mainPagePanel.refreshTable();
				}
			};
		}
		
		private KeyListener createKeyListener() {
			return new KeyListener() {
				
				public void keyTyped(KeyEvent ke) { }
				
				public void keyPressed(KeyEvent ke) {  }
				
				public void keyReleased(KeyEvent ke) {
					mainPagePanel.refreshTable();
				}
			};
		}

		private ItemListener createItemChangeLister(){
			return new ItemListener() {
				
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						mainPagePanel.refreshTable();
					}
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
