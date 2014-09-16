package ar.edu.utn.aadeed.test;

import ar.edu.utn.aadeed.domain.Hotel;
import ar.edu.utn.aadeed.session.Session;
import ar.edu.utn.aadeed.session.SessionFactory;

public class SimpleTest {

	public static void main(String[] args) {
		Session<Hotel> session = SessionFactory.getInstance().getSession(Hotel.class);
		session.add(new Hotel(234L, "Churma"));
	}
}
