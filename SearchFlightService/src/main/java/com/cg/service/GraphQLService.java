package com.cg.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.datafetchers.SearchFlightDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

@Service
public class GraphQLService {

	private GraphQL graphql;

	String inputSchema = "schema{\r\n" + "	query:Query\r\n" + "}\r\n" + "\r\n" + "type Query {\r\n"
			+ "	searchFlight(flightFrom:String,flightTo:String,date:String):[Flight]\r\n" + "}\r\n" + "\r\n"
			+ "type Flight {\r\n" + "	id:String\r\n" + "	flightFrom:String\r\n" + "	flightTo:String\r\n"
			+ "	date:String\r\n" + "	fare:Int\r\n" + "	seatCapacity:Int\r\n" + "}";

	@Autowired
	private SearchFlightDataFetcher searchDataFetcher;

	@PostConstruct
	public GraphQL graphQL() throws IOException {

		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(inputSchema);
		RuntimeWiring runTimeWiring = RuntimeWiring.newRuntimeWiring()
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("searchFlight", searchDataFetcher)).build();
		SchemaGenerator generator = new SchemaGenerator();
		GraphQLSchema gQlSchema = generator.makeExecutableSchema(typeRegistry, runTimeWiring);
		graphql = GraphQL.newGraphQL(gQlSchema).build();
		return graphql;
	}

	public GraphQL getGraphql() {
		return graphql;
	}

}
