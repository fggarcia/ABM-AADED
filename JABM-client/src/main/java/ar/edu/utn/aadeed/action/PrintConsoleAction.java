package ar.edu.utn.aadeed.action;

import ar.edu.utn.aadeed.domain.Hotel;

import com.google.common.eventbus.Subscribe;

public class PrintConsoleAction {
	@Subscribe
	public void update(Hotel hotel) {
		System.out.println(hotel);
	}
}