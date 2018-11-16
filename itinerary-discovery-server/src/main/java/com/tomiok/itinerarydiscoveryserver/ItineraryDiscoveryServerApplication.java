package com.tomiok.itinerarydiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ItineraryDiscoveryServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ItineraryDiscoveryServerApplication.class, args);
  }
}
