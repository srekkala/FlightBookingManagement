package com.cg.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.models.Flight;
import com.cg.repository.AdminFlightRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class getFlightByIdDataFetcher implements DataFetcher<Flight>{

	@Autowired
	private AdminFlightRepository flightRepo;

	
	@Override
	public Flight get(DataFetchingEnvironment environment) {
		int id=environment.getArgument("id");
		return flightRepo.findById(id).get();
	}
	
}
