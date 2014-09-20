package ar.edu.utn.aadeed.test;

import ar.edu.utn.aadeed.domain.Hotel;
import ar.edu.utn.aadeed.domain.Room;
import ar.edu.utn.aadeed.session.SessionFactory;

public class SimpleTest {

	public static void main(String[] args) {
		
		Hotel aHotel = new Hotel(234L, "Churma");
		Room aRoom = new Room(6875L);
		
		SessionFactory.getInstance().getSession(Hotel.class).add(aHotel);
		SessionFactory.getInstance().getSession(Room.class).add(aRoom);
	}
}
