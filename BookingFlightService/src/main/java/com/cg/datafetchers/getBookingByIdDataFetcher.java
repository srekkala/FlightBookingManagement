package com.cg.datafetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.models.Booking;
import com.cg.repository.BookingFlightRepository;
import com.cg.repository.PassengerRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class getBookingByIdDataFetcher implements DataFetcher<Booking>{

	@Autowired
	BookingFlightRepository bookingRepo;	
	@Autowired
	PassengerRepository passengerRepo;
	
	@Override
	public Booking get(DataFetchingEnvironment environment) {
		String bookingId=environment.getArgument("bookingId");
		Booking booking= bookingRepo.findById(bookingId).get();
		return booking;
	}

}
