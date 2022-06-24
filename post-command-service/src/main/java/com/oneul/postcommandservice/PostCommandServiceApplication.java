package com.oneul.postcommandservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PostCommandServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostCommandServiceApplication.class, args);
	}

}
