package ar.edu.utn.aaded.swing;

import ar.edu.utn.aaded.swing.component.JATextBoxComponent;
import ar.edu.utn.aadeed.session.JASessionFactory;

public final class JARegisterComponents {

    static {
        JASessionFactory.getInstance().getViewModuleBuilder()
                .addViewComponent(new JATextBoxComponent())
                .withName("SwingModule").register();
    }

}