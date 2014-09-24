package ar.utn.aaded.swing;

import ar.edu.utn.aadeed.session.JASessionFactory;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.utn.aaded.swing.container.FrameContainerBuilder;
import ar.utn.aaded.swing.domain.Hotel;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String args[]) throws ClassNotFoundException {
        Class.forName(RegisterComponents.class.getName());

        JAViewContainer viewContainer = new FrameContainerBuilder().build();
        JASessionFactory.getInstance().getViewSession(Hotel.class).withContainer(viewContainer).render();
    }
}
