package com.cg.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cg.models.Passenger;

@Repository
public class PassengerRepository{

	public static final String HASH_KEY = "Passenger";
	@Autowired
	private RedisTemplate template;
	
	public String save(Passenger passenger) {
		 template.opsForHash().put(HASH_KEY,passenger.getPassengerId(),passenger);
	        return "Passenger added successfully";
	}



	public List<Passenger> findByBookingId(String bookingId){
		List<Passenger> passengerRes=template.opsForHash().values(HASH_KEY);
		List<Passenger> filteredList = passengerRes.stream()
										  .filter(pass->pass.getBookingId().equals(bookingId))
 										  .collect(Collectors.toList());
		 return filteredList;
	}
}
