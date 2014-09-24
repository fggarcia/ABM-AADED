package ar.edu.utn.aaded.swing.container;

import static com.google.common.base.Preconditions.checkArgument;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

import javax.swing.*;

import java.awt.*;

public class JAFrameContainerBuilder {

	private Integer width;

	private Integer height;

	private LayoutManager layout;

	public JAFrameContainerBuilder withSize(int width, int height) {
		this.height = height;
		this.width = width;
		return this;
	}

	public JAFrameContainerBuilder withLayout(LayoutManager layout) {
		this.layout = layout;
		return this;
	}

	public JAViewContainer build() {
		
		checkArgument(width != null, "width cannot be null");
		checkArgument(height != null, "height cannot be null");
		checkArgument(layout != null, "layout cannot be null");

		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(this.width, this.height);
		
		Container container = frame.getContentPane();
		container.setLayout(this.layout);

		return new FrameContainer(frame);
	}

	public class FrameContainer implements JAViewContainer {

		JFrame frame;

		public FrameContainer(JFrame frame) {
			this.frame = frame;
		}

		public void start() {

		}

		public void addMember(Object member) {
			frame.getContentPane().add(JComponent.class.cast(member));
		}

		public void end() {

		}

		public void render() {
			frame.setVisible(true);
		}
	}
}
