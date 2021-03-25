package com.cg.datafetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.models.Passenger;
import com.cg.repository.BookingFlightRepository;
import com.cg.repository.PassengerRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class getPassengerByIdDataFetcher implements DataFetcher<List<Passenger>>{

	@Autowired
	BookingFlightRepository bookingRepo;	
	@Autowired
	PassengerRepository passengerRepo;
	
	@Override
	public List<Passenger> get(DataFetchingEnvironment environment) {
		String bookingId=environment.getArgument("bookingId");
		List<Passenger> passenger=(List<Passenger>) passengerRepo.findByBookingId(bookingId);
		return passenger;
	}
	
}
