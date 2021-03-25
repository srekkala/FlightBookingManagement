package com.cg.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cg.models.Booking;
import com.cg.models.Flight;

@Repository
public class BookingFlightRepository {
	
	public static final String HASH_KEY = "Booking";
	
	@Autowired
	private RedisTemplate template;
	
	public String save(Booking booking) {
		 template.opsForHash().put(HASH_KEY,booking.getBookingId(),booking);
	        return "BookingId is:" + booking.getBookingId();
	}
	
	public Booking findBookingById(String bookingId){
        return (Booking) template.opsForHash().get(HASH_KEY,bookingId);
    }

}
