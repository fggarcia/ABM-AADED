package ar.edu.utn.aaded.swing;

import ar.edu.utn.aaded.swing.component.TextBoxComponent;
import ar.edu.utn.aadeed.session.JASessionFactory;

public final class RegisterComponents {

    static {
        JASessionFactory.getInstance().getViewModuleBuilder()
                .addViewComponent(new TextBoxComponent())
                .withName("SwingModule").register();
    }

}
