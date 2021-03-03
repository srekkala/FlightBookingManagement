package com.cg.service;

import java.util.HashMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.models.Booking;
import com.cg.models.Passenger;
import com.cg.proxy.FlightProxyService;
import com.cg.repository.BookingFlightRepository;
import com.cg.repository.PassengerRepository;
@Service
public class BookingFlightServiceImpl implements BookingFlightService {

	@Autowired
	PassengerRepository passengerRepo;

	@Autowired
	BookingFlightRepository bookingRepo;

	@Autowired
	FlightProxyService flightProxy;

	@Override
	public String createBooking(int id, Booking booking) {
		//Communication Using FeignClient
		String query1 ="{\n"+
				" getFlight(id:"+id+"){\n"+
				" id\n"+
				" flightFrom\n"+
				" flightTo\n"+
				" date\n"+
				" fare\n"+
				" seatCapacity\n"+
				" }\n"+
				"}\n";
		HashMap<String,Object> flightRes = flightProxy.getFlight(query1);
		String flight = flightRes.get("data").toString();
		System.out.println(flight);
		int index = flight.lastIndexOf("seatCapacity=");// 90
		System.out.println(index);
		int indexx = flight.indexOf(", seatCapacity=");
		System.out.println(indexx);
		String seatcapacity = flight.substring(indexx + 15, indexx + 17);
		System.out.println(seatcapacity);
		int seats = Integer.parseInt(seatcapacity);
		System.out.println(seats);
		System.out.println("after getting flight details from proxy");
		int seatCapacity = seats - booking.getNoOfPassengers();
		System.out.println(id);
		System.out.println(seatCapacity);
		//Communication Using RestTemplate
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		RestTemplate restTemplate = new RestTemplate();
		String URL = "http://localhost:9000/graphql/admin";
		String query2 = "mutation{\n"+
                " updateSeatCapacity(id:"+id+",seatCapacity:"+seatCapacity+"){seatCapacity}\n"+
                "}\n";
		ResponseEntity<String> response2 = restTemplate.postForEntity(URL, new HttpEntity<>(query2, headers),
				String.class);
		System.out.println("The response=================" + response2);
		bookingRepo.save(booking);
		return "BookingId is:" + booking.getBookingId();
	}

	@Override
	public Booking searchBooking(String bookingId) {
		Booking booking = bookingRepo.findById(bookingId).get();
		return booking;
	}

	@Override
	public String createPassenger(Passenger passenger) {
		passengerRepo.save(passenger);
		return "Passenger added successfully";
	}

	@Override
	public List<Passenger> getpassengerByBookingId(String bookingId) {
		return passengerRepo.findByBookingId(bookingId);

	}

}
