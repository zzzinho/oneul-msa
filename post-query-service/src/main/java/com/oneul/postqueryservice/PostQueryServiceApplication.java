package com.oneul.postqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PostQueryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostQueryServiceApplication.class, args);
	}

}
