package com.cg.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.exceptions.FlightNotFoundException;
import com.cg.repository.AdminFlightRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class DeleteFlightDataFetcher implements DataFetcher<String>{
	 
	@Autowired
	private AdminFlightRepository flightRepo;

	@Override
	public String get(DataFetchingEnvironment environment) {
		int id=environment.getArgument("id");
		Boolean flight=flightRepo.existsById(id);
		if(flight==true) {
			flightRepo.deleteById(id);
			return "Flight with id:"+id+"deleted succesfully!";
		}
		else {
			throw new FlightNotFoundException("FlightId with"+id+"not found!");
		}
	}

	
	
}
