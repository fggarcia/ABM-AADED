package ar.edu.utn.aadeed.html.container;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.PrintWriter;

import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public class FormContainerBuilder {

	private PrintWriter writer;
	
	public FormContainerBuilder withPrintWriter(PrintWriter writer) {
		this.writer = writer;
		return this;
	}
	
	public JAViewContainer build() {
		checkArgument(writer != null, "writer cannot be null");
		return new FormContainer(writer);
	}

	public static class FormContainer implements JAViewContainer {

		private PrintWriter writer;
		
		private StringBuffer sb = new StringBuffer();
		
		private FormContainer(PrintWriter writer) {
			this.writer = writer;
		}

		public void start() {
		}

		public void addMember(JAViewComponent member) {
			sb.append("<p>");
			sb.append(member);
			sb.append("</p>");
		}

		public void end() {
		}
		
		public void render() {
			writer.println(sb.toString());
			writer.flush();
		}
	}
}