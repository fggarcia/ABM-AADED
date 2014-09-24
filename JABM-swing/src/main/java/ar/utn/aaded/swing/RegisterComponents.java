package ar.utn.aaded.swing;

import ar.edu.utn.aadeed.session.JASessionFactory;
import ar.utn.aaded.swing.component.TextBoxComponent;

public final class RegisterComponents {

    static {
        JASessionFactory.getInstance().getViewModuleBuilder()
                .addViewComponent(new TextBoxComponent())
                .withName("SwingModule").register();
    }

}
