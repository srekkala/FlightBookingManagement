package com.cg;

import java.util.List;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cg.models.Flight;
import com.cg.repository.FlightRepository;


@SpringBootApplication
public class ServerlessAwsApplication {

	@Autowired
	FlightRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(ServerlessAwsApplication.class, args);
	}
	
	@Bean
	public Supplier<List<Flight>> supply() {
		return () -> repo.findAll();
	}

	@Bean
	public Function<Integer, Flight> function() {
		return input -> repo.findById((Integer)input).get();
	}

}
