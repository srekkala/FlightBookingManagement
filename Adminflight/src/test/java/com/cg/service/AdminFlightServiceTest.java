package com.cg.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.models.Flight;
import com.cg.repository.AdminFlightRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminFlightServiceTest {
	
	@Autowired
	private FlightService service;
	
	@MockBean
	private AdminFlightRepository dao;
	
	@Test
	public void addFlight() {
		Flight flight=new Flight();
		flight.setId(1);
		flight.setFlightFrom("hyderbad");
		flight.setFlightTo("Delhi");
		flight.setSeatCapacity(50);
		flight.setDate("14-02-2021");
		flight.setFare(2000);
		Mockito.when(dao.save(flight)).thenReturn(flight);
		assertThat(service.addFlight(flight)).isEqualTo(flight);
	}
	
	@Test
	public void getAllFlights() {
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
		flight1.setFlightFrom("goa");
		flight1.setFlightTo("hyderabad");
		flight1.setSeatCapacity(50);
		flight1.setDate("14-02-2021");
		flight1.setFare(2000);
		flightList.add(flight);
		flightList.add(flight1);
		Mockito.when(dao.findAll()).thenReturn(flightList);
		assertEquals(2, service.getAllFlights().size());
	}
	
	@Test
	public void deleteFlight() {
		Flight flight=new Flight();
		flight.setId(1);
		flight.setFlightFrom("hyderbad");
		flight.setFlightTo("Delhi");
		flight.setSeatCapacity(50);
		flight.setDate("14-02-2021");
		flight.setFare(3000);
		Mockito.when(dao.existsById(flight.getId())).thenReturn(true);
		assertEquals("Flight with id:"+flight.getId()+"deleted succesfully!", service.deleteFlight(flight.getId()));
	}
	
	@Test
	public void updateSeatCapacity() {
		Flight flight=new Flight();
		flight.setId(1);
		flight.setFlightFrom("hyderbad");
		flight.setFlightTo("Delhi");
		flight.setSeatCapacity(50);
		flight.setDate("14-02-2021");
		flight.setFare(3000);
		Mockito.when(dao.findById(flight.getId())).thenReturn(Optional.of(flight));
		assertThat(service.updateseatCapacity(flight.getId(),flight.getSeatCapacity())).isEqualTo(flight);
		//Exception exception=assertThrows(FlightNotFoundException.class, () -> service.updateFlight(flight.getFlightId(),flight));
		//assertEquals("Flight does not exists!", exception.getMessage());
	}
}