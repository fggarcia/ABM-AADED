package ar.edu.utn.aadeed.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.utn.aadeed.RegisterComponents;
import ar.edu.utn.aadeed.domain.Hotel;
import ar.edu.utn.aadeed.session.SessionFactory;
import ar.edu.utn.aadeed.view.ViewSession;

public class TestServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionFactory.getInstance().getViewSession(Hotel.class);
		
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("");
	}
}