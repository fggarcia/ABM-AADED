package ar.edu.utn.aadeed.front.swing;

import ar.edu.utn.aaded.swing.JARegisterComponents;
import ar.edu.utn.aadeed.JADateUtils;
import ar.edu.utn.aadeed.domain.City;
import ar.edu.utn.aadeed.domain.Hotel;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.session.JASessionFactory;

import java.util.Date;

public class App
{
    public static void main(String args[]) throws ClassNotFoundException {
    	
        Class.forName(JARegisterComponents.class.getName());

        JASession<City> citySession = JASessionFactory.getInstance().getSession(City.class);
        citySession.add(new City(1L, "Buenos Aires"));
        citySession.add(new City(2L, "Miami"));

        JASession<Hotel> hotelSession = JASessionFactory.getInstance().getSession(Hotel.class);
        hotelSession.add(new Hotel(567L, "Churma", "Billinghurst 897", Hotel.Type.HOTEL, true, JADateUtils.truncate(new Date()), 1L));
        hotelSession.add(new Hotel(666L, "Independiente", "Gaona 987", Hotel.Type.APPARTMENT, false, JADateUtils.truncate(new Date()), 2L));

        JASessionFactory.getInstance().getViewSession(Hotel.class).renderMainPagePanel();
    }
}