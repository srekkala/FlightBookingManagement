package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.models.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String>{

	@Query(value="SELECT * FROM passenger p where booking_id =?1",nativeQuery=true)
	List<Passenger> findByBookingId(String bookingId);




}
