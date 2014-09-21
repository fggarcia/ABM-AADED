package ar.edu.utn.aadeed.module;

import java.io.PrintWriter;

import ar.edu.utn.aadeed.view.container.ViewContainer;

public class FormContainer implements ViewContainer<String> {

	private PrintWriter writer;
	
	private StringBuffer sb = new StringBuffer();
	
	public FormContainer(PrintWriter writer) {
		this.writer = writer;
	}

	public void start() {

	}

	public void addMember(String member) {
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
