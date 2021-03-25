package com.cg.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cg.models.Flight;

@Repository
public class SearchFlightRepository {

	public static final String HASH_KEY = "Flight";
	@Autowired
	private RedisTemplate template;

	public List<Flight> searchFlight(String flightFrom,String flightTo,String date){
		List<Flight> flightRes=template.opsForHash().values(HASH_KEY);
		List<Flight> filteredList = flightRes.stream()
										  .filter(fli->fli.getFlightFrom().equals(flightFrom) &&
												fli.getFlightTo().equals(flightTo) && fli.getDate().equals(date))
										  .collect(Collectors.toList());
		 return filteredList;
    }
}
