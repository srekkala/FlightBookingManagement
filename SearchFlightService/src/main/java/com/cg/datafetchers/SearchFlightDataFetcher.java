package com.cg.datafetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.models.Flight;
import com.cg.repository.SearchFlightRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class SearchFlightDataFetcher implements DataFetcher<List<Flight>>{

	@Autowired
	SearchFlightRepository searchrepo;
	
	@Override
	public List<Flight> get(DataFetchingEnvironment environment) {
		String flightFrom=environment.getArgument("flightFrom");
		String flightTo=environment.getArgument("flightTo");
		String date=environment.getArgument("date");
		return searchrepo.searchFlight(flightFrom, flightTo, date);
	}
}
