package ar.edu.utn.aaded.swing;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import ar.edu.utn.aaded.swing.component.JATextBoxComponent;
import ar.edu.utn.aaded.swing.container.JAFrameContainerBuilder;
import ar.edu.utn.aaded.swing.panel.JASearchPanelBuilder;
import ar.edu.utn.aaded.swing.table.JARecordTableBuilder;
import ar.edu.utn.aadeed.builder.JAMainPagePanelBuilder;
import ar.edu.utn.aadeed.session.JASessionFactory;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

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
                .withMainPagePanelBuilder(new JAMainPagePanelBuilder().withTableBuilder(new JARecordTableBuilder()).withContainer(mainFrameContainer).withSearchPanelBuilder(new JASearchPanelBuilder()))
                .withName("SwingModule").register();
    }
}