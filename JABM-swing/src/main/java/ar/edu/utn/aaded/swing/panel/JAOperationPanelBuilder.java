package ar.edu.utn.aaded.swing.panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
            
            JButton updateButton = new JButton("Update");
            updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            updateButton.addActionListener(getUpdateActionListener());
            
            this.containerPanel.add(deleteButton);
            this.containerPanel.add(addButton);
            this.containerPanel.add(updateButton);
        }

		public void renderIn(JAViewContainer<T> container) {
            container.addMember(Box.createRigidArea(new Dimension(0, 10)));
            container.addMember(containerPanel);
            container.addMember(Box.createRigidArea(new Dimension(0, 10)));
        }
        
        private ActionListener getDeleteActionListener() {
        	return new ActionListener() {
    			public void actionPerformed(ActionEvent ae) {
    				
    				T item = mainPagePanel.getSelectedItem();
    				if (item != null) {
    					
    					try {
    						
	    					mainPagePanel.getViewSession().getSession().remove(item);
	    					mainPagePanel.refreshTable();
	    					
                       	} catch (Exception re) {
                    		showErrorDialog(re.getMessage());
                    	}
    				}
    			}
    		};
        }
        
        private ActionListener getAddActionListener() {
        	return new ActionListener() {
    			public void actionPerformed(ActionEvent ae) {

    				try {
    				
	                    T newItem = mainPagePanel.getViewSession().renderAddPanel();
	                    if (newItem != null) {
	                   		mainPagePanel.getViewSession().getSession().add(newItem);
	                   		mainPagePanel.refreshTable();
	                    }

    				} catch (Exception re) {
                    	showErrorDialog(re.getMessage());
                    }
    			}
    		};
        }
        
        private ActionListener getUpdateActionListener() {
        	return new ActionListener() {
    			public void actionPerformed(ActionEvent ae) {
    				
       				T item = mainPagePanel.getSelectedItem();
    				if (item != null) {

    					try {
    						
    						T newItem = mainPagePanel.getViewSession().renderUpdatePanel(item);
    						if (newItem != null) {
                        		mainPagePanel.getViewSession().getSession().update(item, newItem);
                                mainPagePanel.refreshTable();
    						}
                        	
                        } catch (Exception re) {
                        		showErrorDialog(re.getMessage());
                        }
    				}
    			}
    		};
		}
        
        private void showErrorDialog(final String errorMsg) {
        	
        	JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
        }

        private JPanel createContainerPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            return panel;
        }
    }
}