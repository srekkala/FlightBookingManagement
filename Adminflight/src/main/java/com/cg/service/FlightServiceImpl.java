package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.exceptions.FlightNotFoundException;
import com.cg.models.Flight;
import com.cg.repository.AdminFlightRepository;

@Service
public class FlightServiceImpl implements FlightService{

	@Autowired
	AdminFlightRepository flightRepo;
		/*
		 * Function Name : addFlight
		 * Input Parameters : Flight flight
		 * Return Type : Flight 
		 * Description : Adding flights
		 */
		@Override
		public Flight addFlight(Flight flight) {
			return flightRepo.save(flight);
		}
		
		/*
		 * Function Name : deleteFlight
		 * Input Parameters : String flightId
		 * Return Type : String 
		 * Description : Deleting flight based on flilghtId if exists
		 */
		@Override
		public String deleteFlight(int id) {
			boolean isExist = flightRepo.existsById(id);
			if(isExist) {
				flightRepo.deleteById(id);
				return "Flight with id:"+id+"deleted succesfully!";
			}
			else {
				throw new FlightNotFoundException("FlightId with"+id+"not found!");
			}
		}

		
		/*,
		 * Function Name : getAllFlights
		 * Return Type : List<Flight> 
		 * Description : Retrieving all existing flights 
		 */
		@Override
		public List<Flight> getAllFlights() {
			return flightRepo.findAll();
		}

		/*
		 * Function Name : updateFlight
		 * Input Parameters : Flight flight
		 * Return Type : String 
		 * Description : Updating existing flight 
		 */
		@Override
		public Flight updateseatCapacity(int id, int seatCapacity) {
			if(flightRepo.findById(id) != null) {
				Flight flightRes=flightRepo.findById(id).get();
				flightRes.setSeatCapacity(seatCapacity);
				flightRepo.save(flightRes);
				return flightRes;
				}
				else {
					throw new FlightNotFoundException("FlightId with"+id+"not found!");
				}
		}
}
	