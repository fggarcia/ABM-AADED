package ar.edu.utn.aaded.swing.container;

import static com.google.common.base.Preconditions.checkArgument;

import java.awt.Container;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.apache.commons.lang.NotImplementedException;

import ar.edu.utn.aadeed.view.JAViewSession;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.container.builder.JAViewContainerBuilder;

import com.google.common.base.Function;

public class JAFrameContainerBuilder implements JAViewContainerBuilder {

	private Function<JFrame, LayoutManager> layout;

	public JAFrameContainerBuilder withLayout(Function<JFrame, LayoutManager> layout) {
		this.layout = layout;
		return this;
	}

	public <T> JAViewContainer build(JAViewSession<T> viewSession) {

		checkArgument(layout != null, "layout cannot be null");

		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = frame.getContentPane();
		container.setLayout(layout.apply(frame));

		return new JAFrameContainer(frame);
	}

	private static class JAFrameContainer implements JAViewContainer {

		private JFrame frame;

		public JAFrameContainer(JFrame frame) {
			this.frame = frame;
		}

		public void addMember(Object member) {
			frame.getContentPane().add(JComponent.class.cast(member));
		}

		public void render() {
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			frame.pack();
			frame.setVisible(true);
		}

		public void addMember(JAMember member) {
			throw new NotImplementedException();
		}
	}
}
