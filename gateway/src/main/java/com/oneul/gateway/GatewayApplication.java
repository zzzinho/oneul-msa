package com.oneul.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;

@SpringBootApplication
public class GatewayApplication {
	@Autowired
	RouteDefinitionLocator locator;

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}	
}
