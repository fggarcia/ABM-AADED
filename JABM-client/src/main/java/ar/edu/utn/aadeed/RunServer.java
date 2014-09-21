package ar.edu.utn.aadeed;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import ar.edu.utn.aadeed.servlet.TestServlet;

public class RunServer {
	
	public static void main(String[] args) {
		startJetty();
	}

	static void startJetty() {
		try {
			
			Server server = new Server();
			Connector con = new SelectChannelConnector();
			con.setPort(8080);
			server.addConnector(con);

			ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	        context.setContextPath("/");
	        server.setHandler(context);
	 
	        context.addServlet(new ServletHolder(new TestServlet()),"/*");
			
			server.start();
			server.join();
			
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
}
