package ar.edu.utn.aadeed.front.html.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.utn.aadeed.domain.Hotel;
import ar.edu.utn.aadeed.html.container.FormContainerBuilder;
import ar.edu.utn.aadeed.session.JASessionFactory;
import ar.edu.utn.aadeed.view.container.JAViewContainer;

public class BasicServlet extends HttpServlet {

	private static final long serialVersionUID = -2742844683614754112L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		
		JAViewContainer container = new FormContainerBuilder().withPrintWriter(response.getWriter()).build();
		JASessionFactory.getInstance().getViewSession(Hotel.class).render(container);
	}
}