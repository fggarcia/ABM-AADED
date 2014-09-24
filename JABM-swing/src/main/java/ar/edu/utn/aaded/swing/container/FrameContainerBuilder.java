package ar.edu.utn.aaded.swing.container;

import ar.edu.utn.aadeed.view.container.JAViewContainer;

import javax.swing.*;
import java.awt.*;

public class FrameContainerBuilder {
    public JAViewContainer build(){
        JFrame appFrame = new JFrame();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(100, 100);
        Container container = appFrame.getContentPane();
        container.setLayout(new GridLayout(0,2));

        return new FrameContainer(appFrame);
    }

    public class FrameContainer implements JAViewContainer{

        JFrame frame;

        public FrameContainer(JFrame frame){
            this.frame = frame;
        }

        public void start() {

        }

        public void addMember(Object member) {
            frame.getContentPane().add(JComponent.class.cast(member));
        }

        public void end() {

        }

        public void render() {
            frame.setVisible(true);
        }
    }
}
