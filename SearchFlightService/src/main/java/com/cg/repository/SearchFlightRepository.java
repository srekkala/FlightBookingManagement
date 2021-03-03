package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.models.Flight;

@Repository
public interface SearchFlightRepository extends JpaRepository<Flight, Integer> {

	@Query(value="SELECT * FROM flight f where flight_from =?1 AND flight_to =?2 AND date =?3",nativeQuery=true)
	public List<Flight> searchFlight(String flightFrom,String flightTo,String date);
}
