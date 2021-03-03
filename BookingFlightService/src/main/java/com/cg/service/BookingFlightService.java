package com.cg.service;

import java.util.List;


import com.cg.models.Booking;
import com.cg.models.Passenger;


public interface BookingFlightService {

	String createBooking(int id,Booking booking);
	String createPassenger(Passenger passenger);
	Booking searchBooking(String bookingId);
	List<Passenger> getpassengerByBookingId(String bookingId);
}
