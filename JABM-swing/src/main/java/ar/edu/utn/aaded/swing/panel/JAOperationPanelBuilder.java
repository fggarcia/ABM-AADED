package ar.edu.utn.aaded.swing.panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.panel.JAViewOperationPanel;
import ar.edu.utn.aadeed.view.panel.builder.JAViewOperationPanelBuilder;

public class JAOperationPanelBuilder implements JAViewOperationPanelBuilder {

    public <T> JAViewOperationPanel<T> build(JAMainPagePanel<T> mainPagePanel) {
        return new JAOperationPanel<T>(mainPagePanel);
    }

    private static class JAOperationPanel<T> implements JAViewOperationPanel<T> {

        private JPanel containerPanel;

        private JAMainPagePanel<T> mainPagePanel;

        public JAOperationPanel(JAMainPagePanel<T> mainPagePanel) {

            this.mainPagePanel = mainPagePanel;
            this.containerPanel = createContainerPanel();
            
            JButton deleteButton = new JButton("Delete");
            deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            deleteButton.addActionListener(getDeleteActionListener());
            
            JButton addButton = new JButton("Add");
            addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            addButton.addActionListener(getAddActionListener());
            
            this.containerPanel.add(deleteButton);
            this.containerPanel.add(addButton);
        }

        public void render(JAViewContainer container) {
            container.addMember(Box.createRigidArea(new Dimension(0, 10)));
            container.addMember(containerPanel);
            container.addMember(Box.createRigidArea(new Dimension(0, 10)));
        }
        
        private ActionListener getDeleteActionListener() {
        	return new ActionListener() {
    			public void actionPerformed(ActionEvent ae) {
    				T item = mainPagePanel.getSelectedItem();
    				if (item != null) {
    					mainPagePanel.getSession().remove(item);
    					mainPagePanel.refreshTable();
    				}
    			}
    		};
        }
        
        private ActionListener getAddActionListener() {
        	return new ActionListener() {
    			public void actionPerformed(ActionEvent ae) {
    			}
    		};
        }

        private JPanel createContainerPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            return panel;
        }
    }
}
