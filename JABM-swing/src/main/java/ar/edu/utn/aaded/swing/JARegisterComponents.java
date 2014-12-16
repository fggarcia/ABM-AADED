package ar.edu.utn.aaded.swing;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;

import ar.edu.utn.aaded.swing.component.JACheckBoxComponent;
import ar.edu.utn.aaded.swing.component.JADateComponent;
import ar.edu.utn.aaded.swing.component.JAImageComponent;
import ar.edu.utn.aaded.swing.component.JASelectItemComponent;
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
import ar.edu.utn.aadeed.view.panel.builder.JAUpdatePagePanelBuilder;

import com.google.common.base.Function;

public final class JARegisterComponents {

    static {

        JAViewContainerBuilder mainFrameContainerBuilder = new JAFrameContainerBuilder().withLayout(new Function<JInternalFrame, LayoutManager>() {
            public LayoutManager apply(JInternalFrame input) {
                return new BoxLayout(input.getContentPane(), BoxLayout.Y_AXIS);
            }
        }).withDesktop(JADesktop.getInstance().getDesktop());
        
        JAMainPagePanelBuilder mainPagePanelBuilder = new JAMainPagePanelBuilder()
        	.withTableBuilder(new JARecordTableBuilder())
            .withContainerBuilder(mainFrameContainerBuilder)
            .withSearchPanelBuilder(new JASearchPanelBuilder())
            .withOperationPanelBuilder(new JAOperationPanelBuilder());
        
        JAAddPagePanelBuilder addPagePanelBuilder = new JAAddPagePanelBuilder()
        	.withContainerBuilder(new JAPopupContainerBuilder().withTitle("Add Item"));
        
        JAUpdatePagePanelBuilder updatePagePanelBuilder = new JAUpdatePagePanelBuilder()
    	.withContainerBuilder(new JAPopupContainerBuilder().withTitle("Update Item"));

        JASessionFactory.getInstance().getViewModuleBuilder()
                .addViewComponent(new JATextBoxComponent())
                .addViewComponent(new JASelectItemComponent())
                .addViewComponent(new JACheckBoxComponent())
                .addViewComponent(new JADateComponent())
                .addViewComponent(new JAImageComponent())
                .withMainPagePanelBuilder(mainPagePanelBuilder)
                .withAddPagePanelBuilder(addPagePanelBuilder)
                .withUpdatePagePanelBuilder(updatePagePanelBuilder)
                .withName("SwingModule").register();
    }
}