package ar.edu.utn.aadeed.html.container;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.PrintWriter;

import org.apache.commons.lang.NotImplementedException;

import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public class FormContainerBuilder {

	private PrintWriter writer;
	
	public FormContainerBuilder withPrintWriter(PrintWriter writer) {
		this.writer = writer;
		return this;
	}
	
	public <T> JAViewContainer<T> build() {
		checkArgument(writer != null, "writer cannot be null");
		return new FormContainer<T>(writer);
	}

	public static class FormContainer<T> implements JAViewContainer<T> {

		private PrintWriter writer;
		
		private StringBuffer sb = new StringBuffer();
		
		private FormContainer(PrintWriter writer) {
			this.writer = writer;
		}

		public void addMember(Object member) {
			sb.append("<p>");
			sb.append(member);
			sb.append("</p>");
		}

		public void render() {
			writer.println(sb.toString());
			writer.flush();
		}

		public T renderAndReturn() {
			throw new NotImplementedException();
		}

		public void addMember(JAMember member) {
			// TODO Auto-generated method stub
			
		}
	}
}