package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.models.Flight;

@Repository
public interface AdminFlightRepository extends JpaRepository<Flight, Integer> {

//	@Modifying
//	@Query("update Flight SET seatCapacity = ?1 where id=?0")
//	public String updateSeatCapacity(int id, int seatCapacity);
}
