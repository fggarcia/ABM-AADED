package ar.edu.utn.aadeed.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.utn.aadeed.container.FormContainerBuilder;
import ar.edu.utn.aadeed.domain.Room;
import ar.edu.utn.aadeed.session.SessionFactory;
import ar.edu.utn.aadeed.view.container.ViewContainer;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = -2742844683614754112L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		
		ViewContainer container = new FormContainerBuilder().withPrintWriter(response.getWriter()).build();
		SessionFactory.getInstance().getViewSession(Room.class).withContainer(container).render();
	}
}