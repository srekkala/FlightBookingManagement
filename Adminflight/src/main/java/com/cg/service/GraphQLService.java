package com.cg.service;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.datafetchers.AddFlightDataFetcher;
import com.cg.datafetchers.DeleteFlightDataFetcher;
import com.cg.datafetchers.UpdateDataFetcher;
import com.cg.datafetchers.getAllFlightsDataFetcher;
import com.cg.datafetchers.getFlightByIdDataFetcher;

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

	String schemaInput = "schema{\r\n" + "	query:Query\r\n" + "	mutation:Mutation\r\n" + "}\r\n" + "\r\n" + "\r\n"
			+ "type Query {\r\n" + "	getFlight(id:Int):Flight\r\n" + "	getAllFlights:[Flight]\r\n" + "}\r\n"
			+ "\r\n" + "type Mutation{\r\n"
			+ "	addFlight(id:Int,flightFrom:String,flightTo:String,date:String,fare:Int,seatCapacity:Int) : Flight\r\n"
			+ "	updateSeatCapacity(id:Int,seatCapacity:Int):Flight\r\n" + "	deleteFlight(id:Int):String\r\n" + "}\r\n"
			+ "\r\n" + "type Flight {\r\n" + "	id:Int\r\n" + "	flightFrom:String\r\n" + "	flightTo:String\r\n"
			+ "	date:String\r\n" + "	fare:Int\r\n" + "	seatCapacity:Int\r\n" + "}";

	@Autowired
	private AddFlightDataFetcher addDatafetcher;
	@Autowired
	private getFlightByIdDataFetcher getDatafetcher;

	@Autowired
	private getAllFlightsDataFetcher getAllDataFetcher;

	@Autowired
	private UpdateDataFetcher updateDatafetcher;

	@Autowired
	private DeleteFlightDataFetcher deleteDataFetcher;

	@PostConstruct
	public GraphQL graphQL() throws IOException {
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaInput);
		RuntimeWiring runTimeWiring = RuntimeWiring.newRuntimeWiring()
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getFlight", getDatafetcher)
						.dataFetcher("getAllFlights", getAllDataFetcher))
				.type(TypeRuntimeWiring.newTypeWiring("Mutation").dataFetcher("addFlight", addDatafetcher)
						.dataFetcher("updateSeatCapacity", updateDatafetcher)
						.dataFetcher("deleteFlight", deleteDataFetcher))
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
