package com.cg.service;

import java.util.List;

import com.cg.models.Flight;


public interface FlightService {

	 Flight addFlight(Flight flight);
	 String deleteFlight(int id);
	 List<Flight> getAllFlights();
	 Flight updateseatCapacity(int id, int seatCapacity);
	 
}
