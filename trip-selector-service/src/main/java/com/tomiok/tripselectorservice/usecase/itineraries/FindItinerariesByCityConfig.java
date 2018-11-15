package com.tomiok.tripselectorservice.usecase.itineraries;

import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindItinerariesByCityConfig {

  private final ItineraryProxy itineraryProxy;

  public FindItinerariesByCityConfig(final ItineraryProxy itineraryProxy) {
    this.itineraryProxy = itineraryProxy;
  }

  @Bean
  public FindItinerariesByCity findItinerariesByCity() {
    return new FindItineraryByCityImpl(itineraryProxy);
  }
}
