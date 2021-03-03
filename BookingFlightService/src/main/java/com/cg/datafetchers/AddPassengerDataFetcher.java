package com.cg.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.models.Passenger;
import com.cg.repository.BookingFlightRepository;
import com.cg.repository.PassengerRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class AddPassengerDataFetcher implements DataFetcher<String>{

	@Autowired
	PassengerRepository passengerRepo;
	
	@Autowired
	BookingFlightRepository bookingRepo;

		@Override
		public String get(DataFetchingEnvironment environment) {
			String passengerId=environment.getArgument("passengerId");
			String firstName=environment.getArgument("firstName");
			String lastName=environment.getArgument("lastName");
			String gender=environment.getArgument("gender");
			String bookingId=environment.getArgument("bookingId");
			Passenger passenger = new Passenger(passengerId,firstName,lastName,gender,bookingId);
			passengerRepo.save(passenger);
			return "Passenger added successfully";
		}
		
		
}
