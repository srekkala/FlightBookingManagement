package com.cg.datafetchers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.models.Booking;
import com.cg.proxy.FlightProxyService;
import com.cg.repository.BookingFlightRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Service
public class AddDataFetcher implements DataFetcher<String> {

	@Autowired
	BookingFlightRepository bookingRepo;

	@Autowired
	FlightProxyService flightProxy;
	@Override
	public String get(DataFetchingEnvironment environment) {
		int id=environment.getArgument("id");
		String bookingId=environment.getArgument("bookingId");
		String userId=environment.getArgument("userId");
		int noOfPassengers=environment.getArgument("noOfPassengers");
		Booking booking=new Booking(bookingId,userId,noOfPassengers);
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
				int index = flight.lastIndexOf("seatCapacity=");// 90
				int indexx = flight.indexOf(", seatCapacity=");
				String seatcapacity = flight.substring(indexx + 15, indexx + 17);
				System.out.println(seatcapacity);
				int seats = Integer.parseInt(seatcapacity);
				System.out.println(seats);
				System.out.println("after getting flight details from proxy");
				int seatCapacity = seats - booking.getNoOfPassengers();
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
				return bookingRepo.save(booking);
			}


}
