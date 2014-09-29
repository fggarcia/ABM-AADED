package ar.edu.utn.aadeed.front.swing;

import java.awt.GridLayout;

import ar.edu.utn.aaded.swing.JARegisterComponents;
import ar.edu.utn.aaded.swing.container.JAFrameContainerBuilder;
import ar.edu.utn.aadeed.domain.Hotel;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.session.JASessionFactory;
import ar.edu.utn.aadeed.view.container.JAViewContainer;


public class App
{
    public static void main(String args[]) throws ClassNotFoundException {
    	
        Class.forName(JARegisterComponents.class.getName());
        
        JASession<Hotel> hotelSession = JASessionFactory.getInstance().getSession(Hotel.class);
        hotelSession.add(new Hotel(567L, "Churma"));
        
        JAViewContainer viewContainer = new JAFrameContainerBuilder().withLayout(new GridLayout(0, 2)).build();
        JASessionFactory.getInstance().getViewSession(Hotel.class).renderRecordTable(viewContainer, hotelSession.getFiltersBuilder().search());
    }
}