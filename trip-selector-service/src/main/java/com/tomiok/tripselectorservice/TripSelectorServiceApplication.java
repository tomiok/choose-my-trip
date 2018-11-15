package com.tomiok.tripselectorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TripSelectorServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TripSelectorServiceApplication.class, args);
  }
}
