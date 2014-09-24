package ar.edu.utn.aadeed.front.swing;

import ar.edu.utn.aaded.swing.RegisterComponents;
import ar.edu.utn.aaded.swing.container.FrameContainerBuilder;
import ar.edu.utn.aadeed.domain.Hotel;
import ar.edu.utn.aadeed.session.JASessionFactory;
import ar.edu.utn.aadeed.view.container.JAViewContainer;


public class App
{
    public static void main(String args[]) throws ClassNotFoundException {
        Class.forName(RegisterComponents.class.getName());

        JAViewContainer viewContainer = new FrameContainerBuilder().build();
        JASessionFactory.getInstance().getViewSession(Hotel.class).withContainer(viewContainer).render();
    }
}
