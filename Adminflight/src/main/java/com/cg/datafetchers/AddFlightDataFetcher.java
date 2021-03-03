package com.cg.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.exceptions.FlightException;
import com.cg.models.Flight;
import com.cg.repository.AdminFlightRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class AddFlightDataFetcher implements DataFetcher<Flight> {

	@Autowired
	private AdminFlightRepository flightRepo;
	@Override
	public Flight get(DataFetchingEnvironment environment) {
		String flightFrom=environment.getArgument("flightFrom");
		String flightTo=environment.getArgument("flightTo");
		String date=environment.getArgument("date");
		int fare=environment.getArgument("fare");
		int seatCapacity=environment.getArgument("seatCapacity");
		Flight flight =new Flight(0, flightFrom, flightTo, date, fare, seatCapacity);
		if (flightRepo.existsById(flight.getId())) {
			throw new FlightException("Flight with id: " + flight.getId() + "already available");
		}
		flightRepo.save(flight);
		return flight;
	}

}
