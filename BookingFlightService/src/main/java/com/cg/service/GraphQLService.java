package com.cg.service;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.datafetchers.AddDataFetcher;
import com.cg.datafetchers.AddPassengerDataFetcher;
import com.cg.datafetchers.getBookingByIdDataFetcher;
import com.cg.datafetchers.getPassengerByIdDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

@Service
@Transactional
public class GraphQLService {

	private GraphQL graphql;

	String schemaInput = "schema{\r\n" + "	query:Query\r\n" + "	mutation:Mutation\r\n" + "}\r\n" + "\r\n"
			+ "type Query {\r\n" + "	getBooking(bookingId:String):Booking\r\n"
			+ "	getPassenger(bookingId:String):[Passenger]\r\n" + "}\r\n" + "\r\n" + "type Mutation{\r\n"
			+ "	addBooking(id:Int,bookingId:String,userId:String,noOfPassengers:Int) : String\r\n"
			+ "	addPassengers(passengerId:String,firstName:String,lastName:String,gender:String,bookingId:String):String\r\n"
			+ "}\r\n" + "\r\n" + "type Flight {\r\n" + "	id:Int\r\n" + "	flightFrom:String\r\n"
			+ "	flightTo:String\r\n" + "	date:String\r\n" + "	fare:Int\r\n" + "	seatCapacity:Int\r\n" + "}\r\n"
			+ "\r\n" + "type Booking{\r\n" + "	bookingId:String\r\n" + "	noOfPassengers:Int\r\n"
			+ "	passenger:[Passenger]\r\n" + "	flight:Flight\r\n" + "}\r\n" + "\r\n" + "type Passenger{\r\n"
			+ "	passengerId:String\r\n" + "	firstName:String\r\n" + "	lastName:String\r\n" + "	gender:String\r\n"
			+ "	bookingId:String\r\n" + "}\r\n" + "\r\n" + "\r\n" + "\r\n" + "";

	@Autowired
	private AddDataFetcher flightDatafetcher;

	@Autowired
	AddPassengerDataFetcher passengerDatafetcher;

	@Autowired
	private getBookingByIdDataFetcher getBookingDatafetcher;

	@Autowired
	private getPassengerByIdDataFetcher getPassengerDataFetcher;

	@PostConstruct
	public GraphQL graphQL() throws IOException {

		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaInput);
		RuntimeWiring runTimeWiring = RuntimeWiring.newRuntimeWiring()
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getBooking", getBookingDatafetcher)
						.dataFetcher("getPassenger", getPassengerDataFetcher))
				.type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("addBooking", flightDatafetcher)
						.dataFetcher("addPassengers", passengerDatafetcher))
				.build();
		SchemaGenerator generator = new SchemaGenerator();
		GraphQLSchema gQlSchema = generator.makeExecutableSchema(typeRegistry, runTimeWiring);
		graphql = GraphQL.newGraphQL(gQlSchema).build();
		return graphql;
	}

	public GraphQL getGraphql() {
		return graphql;
	}

}