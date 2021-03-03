package com.cg.service;

import java.util.List;

import com.cg.models.Flight;

public interface SearchFlightServiceI {

	List<Flight> searchFlight(String flightFrom,String flightTo,String date);
}
