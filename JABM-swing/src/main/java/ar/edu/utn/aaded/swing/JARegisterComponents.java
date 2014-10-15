package ar.edu.utn.aaded.swing;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import ar.edu.utn.aaded.swing.component.JATextBoxComponent;
import ar.edu.utn.aaded.swing.container.JAFrameContainerBuilder;
import ar.edu.utn.aaded.swing.container.JAPopupContainerBuilder;
import ar.edu.utn.aaded.swing.panel.JAOperationPanelBuilder;
import ar.edu.utn.aaded.swing.panel.JASearchPanelBuilder;
import ar.edu.utn.aaded.swing.table.JARecordTableBuilder;
import ar.edu.utn.aadeed.session.JASessionFactory;
import ar.edu.utn.aadeed.view.container.builder.JAViewContainerBuilder;
import ar.edu.utn.aadeed.view.panel.builder.JAAddPagePanelBuilder;
import ar.edu.utn.aadeed.view.panel.builder.JAMainPagePanelBuilder;

import com.google.common.base.Function;

public final class JARegisterComponents {

    static {

        JAViewContainerBuilder mainFrameContainerBuilder = new JAFrameContainerBuilder().withLayout(new Function<JFrame, LayoutManager>() {
            public LayoutManager apply(JFrame input) {
                return new BoxLayout(input.getContentPane(), BoxLayout.Y_AXIS);
            }
        });
        
        JAMainPagePanelBuilder mainPagePanelBuilder = new JAMainPagePanelBuilder()
        	.withTableBuilder(new JARecordTableBuilder())
            .withContainerBuilder(mainFrameContainerBuilder)
            .withSearchPanelBuilder(new JASearchPanelBuilder())
            .withOperationPanelBuilder(new JAOperationPanelBuilder());
        
        JAAddPagePanelBuilder addPagePanelBuilder = new JAAddPagePanelBuilder()
        	.withContainerBuilder(new JAPopupContainerBuilder());

        JASessionFactory.getInstance().getViewModuleBuilder()
                .addViewComponent(new JATextBoxComponent())
                .withMainPagePanelBuilder(mainPagePanelBuilder)
                .withAddPagePanelBuilder(addPagePanelBuilder)
                .withName("SwingModule").register();
    }
}