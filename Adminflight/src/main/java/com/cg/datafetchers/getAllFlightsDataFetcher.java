package com.cg.datafetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.models.Flight;
import com.cg.repository.AdminFlightRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class getAllFlightsDataFetcher implements DataFetcher<List<Flight>>{

	@Autowired
	private AdminFlightRepository flightRepo;
	
	@Override
	public List<Flight> get(DataFetchingEnvironment environment) {
		return flightRepo.findAll();
	}

}
