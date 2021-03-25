package com.cg.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.cg.models.Flight;

@Repository
public class AdminFlightRepository {

	public static final String HASH_KEY = "Flight";
	@Autowired
	private RedisTemplate template;

	public Flight save(Flight flight){
	        template.opsForHash().put(HASH_KEY,flight.getId(),flight);
	        return flight;
	    }

	public List<Flight> findAll(){
	        return template.opsForHash().values(HASH_KEY);
	    }

	public Flight findFlightById(int id){
	        return (Flight) template.opsForHash().get(HASH_KEY,id);
	    }

	public String deleteFlight(int id){
	         template.opsForHash().delete(HASH_KEY,id);
	        return "Flight with id:"+id+ " deleted succesfully!";
	    }

}
