package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.models.Flight;
import com.cg.repository.SearchFlightRepository;

@Service
public class SearchFlightServiceImpl implements SearchFlightServiceI {
	@Autowired
	SearchFlightRepository searchrepo;
	
	@Override
	public List<Flight> searchFlight(String flightFrom,String flightTo,String date) {
		return searchrepo.searchFlight(flightFrom, flightTo, date);
	}
	
}
