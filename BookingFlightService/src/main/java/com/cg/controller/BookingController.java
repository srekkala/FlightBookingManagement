package com.cg.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.service.GraphQLService;

import graphql.ExecutionResult;

@RestController
public class BookingController {

		@Autowired
		GraphQLService graphqlService;
		@PostMapping(value="/graphql/admin",produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Map<String,Object>> getBooking(@RequestBody String query){
			ExecutionResult result =graphqlService.getGraphql().execute(query);
		return new ResponseEntity<>(result.toSpecification(),HttpStatus.OK);
		}
}
