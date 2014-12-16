package ar.edu.utn.aaded.swing.container;

import static com.google.common.base.Preconditions.checkArgument;

import java.awt.Container;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import org.apache.commons.lang.NotImplementedException;

import ar.edu.utn.aadeed.view.JAViewSession;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.container.builder.JAViewContainerBuilder;

import com.google.common.base.Function;

public class JAFrameContainerBuilder implements JAViewContainerBuilder {

	private Function<JInternalFrame, LayoutManager> layout;
	
	private JDesktopPane desktop;

	public JAFrameContainerBuilder withLayout(Function<JInternalFrame, LayoutManager> layout) {
		this.layout = layout;
		return this;
	}
	
	public JAFrameContainerBuilder withDesktop(final JDesktopPane desktop) {
		this.desktop = desktop;
		return this;
	}

	public <T> JAViewContainer<T> build(JAViewSession<T> viewSession) {

		checkArgument(layout != null, "layout cannot be null");
		checkArgument(desktop != null, "desktop cannot be null");

		JInternalFrame frame = new JInternalFrame(viewSession.getSession().getRepresentationFor().getSimpleName(), true, true, true, true);

		Container container = frame.getContentPane();
		container.setLayout(layout.apply(frame));

		return new JAFrameContainer<T>(frame, desktop);
	}

	private static class JAFrameContainer<T> implements JAViewContainer<T> {

		private JInternalFrame frame;
		
		private JDesktopPane desktop;

		public JAFrameContainer(JInternalFrame frame, JDesktopPane desktop) {
			this.frame = frame;
			this.desktop = desktop;
		}

		public void addMember(Object member) {
			frame.getContentPane().add(JComponent.class.cast(member));
		}

		public void render() {
			
			frame.setResizable(false);
			frame.pack();
			frame.setVisible(true);
			
			desktop.add(frame);
		}

		public T renderAndReturn() {
			throw new NotImplementedException();
		}

		public void addMember(JAMember member) {
			throw new NotImplementedException();
		}
	}
}
