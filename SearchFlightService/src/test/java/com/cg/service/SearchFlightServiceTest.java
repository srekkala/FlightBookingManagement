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

import com.cg.models.Flight;
import com.cg.repository.SearchFlightRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchFlightServiceTest {

	@Autowired
	SearchFlightServiceI service;
	
	@MockBean
	SearchFlightRepository repo;
	
	@Test
	public void searchFlight() {
		List<Flight> flightList=new ArrayList<Flight>();
		Flight flight=new Flight();
		flight.setId(1);
		flight.setFlightFrom("hyderbad");
		flight.setFlightTo("Delhi");
		flight.setSeatCapacity(50);
		flight.setDate("14-02-2021");
		flight.setFare(3000);
		Flight flight1=new Flight();
		flight1.setId(2);
		flight1.setFlightFrom("hyderbad");
		flight1.setFlightTo("Delhi");
		flight1.setSeatCapacity(50);
		flight1.setDate("14-02-2021");
		flight1.setFare(2000);
		flightList.add(flight);
		flightList.add(flight1);
		String flightFrom="hyderabad";
		String flightTo="Delhi";
		String flightDate="14-02-2021";
		Mockito.when(repo.searchFlight(flightFrom, flightTo, flightDate)).thenReturn(flightList);
		assertThat(service.searchFlight(flightFrom, flightTo, flightDate)).isEqualTo(flightList);
	}
	
}
