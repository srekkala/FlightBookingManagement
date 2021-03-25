package com.cg.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.exceptions.FlightNotFoundException;
import com.cg.models.Flight;
import com.cg.repository.AdminFlightRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class UpdateDataFetcher implements DataFetcher<Flight>{

	@Autowired
	private AdminFlightRepository flightRepo;

	@Override
	public Flight get(DataFetchingEnvironment environment){
		int id=environment.getArgument("id");
		int seatCapacity=environment.getArgument("seatCapacity");
		if(flightRepo.findFlightById(id) != null) {
			Flight flightRes=flightRepo.findFlightById(id);
			flightRes.setSeatCapacity(seatCapacity);
			flightRepo.save(flightRes);
			return flightRes;
			}
			else {
				throw new FlightNotFoundException("FlightId with"+id+"not found!");
			}
	}
	
	
}
