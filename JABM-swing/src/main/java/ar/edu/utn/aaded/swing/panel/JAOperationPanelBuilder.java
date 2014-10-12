package ar.edu.utn.aaded.swing.panel;

import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;
import ar.edu.utn.aadeed.view.panel.JAViewOperationPanel;
import ar.edu.utn.aadeed.view.panel.builder.JAViewOperationPanelBuilder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;

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

            this.containerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            this.containerPanel.add(new JButton("Fede y el Pato"));
            this.containerPanel.add(new JButton("Fede Gil"));
        }

        public void render(JAViewContainer container) {
            container.addMember(Box.createRigidArea(new Dimension(0, 20)));
            container.addMember(containerPanel);
        }

        private JPanel createContainerPanel() {

            JPanel panel = new JPanel();
            BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
            panel.setLayout(layout);

            Border margin = new EmptyBorder(10, 10, 10, 80);
            Border border = BorderFactory.createEmptyBorder();
            panel.setBorder(new CompoundBorder(border, margin));

            return panel;
        }
    }
}
