package ar.edu.utn.aaded.swing;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import ar.edu.utn.aaded.swing.component.JATextBoxComponent;
import ar.edu.utn.aaded.swing.container.JAFrameContainerBuilder;
import ar.edu.utn.aaded.swing.panel.JASearchPanel;
import ar.edu.utn.aaded.swing.table.JARecordTableBuilder;
import ar.edu.utn.aadeed.session.JASessionFactory;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.panel.JAMainPagePanel;

import com.google.common.base.Function;

public final class JARegisterComponents {

    static {
    	
    	JAViewContainer mainFrameContainer = new JAFrameContainerBuilder().withLayout(new Function<JFrame, LayoutManager>() {
			public LayoutManager apply(JFrame input) {
				return new BoxLayout(input.getContentPane(), BoxLayout.Y_AXIS);
			}
		}).build();
    	
        JASessionFactory.getInstance().getViewModuleBuilder()
                .addViewComponent(new JATextBoxComponent())
                .withMainPagePanel(new JAMainPagePanel().withTableBuilder(new JARecordTableBuilder()).withContainer(mainFrameContainer).withSearchPanel(new JASearchPanel()))
                .withName("SwingModule").register();
    }
}