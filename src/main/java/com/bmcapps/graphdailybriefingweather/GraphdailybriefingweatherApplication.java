package com.bmcapps.graphdailybriefingweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GraphdailybriefingweatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphdailybriefingweatherApplication.class, args);
	}

}
