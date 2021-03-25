package com.cg.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.models.Booking;
import com.cg.models.Passenger;
import com.cg.repository.BookingFlightRepository;
import com.cg.repository.PassengerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingFlightServiceTests {

	@Autowired
	BookingFlightService service;

	@MockBean
	private BookingFlightRepository bookingRepo;
	@MockBean
	private PassengerRepository passengerRepo;

	@Test
	public void bookingSearch() {
		Booking booking = new Booking();
		booking.setBookingId("1246");
		booking.setUserId("SAI");
		booking.setNoOfPassengers(1);
		Mockito.when(bookingRepo.findBookingById(booking.getBookingId())).thenReturn(booking);
		assertThat(service.searchBooking(booking.getBookingId())).isEqualTo(booking);

	}

	@Test
	public void PassengerSearch() {
		Passenger passenger = new Passenger();
		List<Passenger> passengerList = new ArrayList<Passenger>();
		passenger.setFirstName("Namada");
		passenger.setLastName("pragnya");
		passenger.setGender("female");
		passenger.setPassengerId("1");
		passengerList.add(passenger);
		Mockito.when(passengerRepo.findByBookingId(passenger.getBookingId())).thenReturn(passengerList);
		assertThat(service.getpassengerByBookingId(passenger.getBookingId())).isEqualTo(passengerList);
	}

}

