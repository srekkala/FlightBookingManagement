package com.cg.proxy;

import java.util.HashMap;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="AdminFlight-microservice",url = "localhost:9000")
public interface FlightProxyService {
	
	@PostMapping("/graphql/admin")
	public HashMap<String, Object> getFlight(@RequestBody String query1);
	

	
	
}
