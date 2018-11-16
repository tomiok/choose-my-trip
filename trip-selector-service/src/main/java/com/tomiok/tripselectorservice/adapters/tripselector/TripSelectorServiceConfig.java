package com.tomiok.tripselectorservice.adapters.tripselector;

import com.tomiok.tripselectorservice.usecase.itineraries.FindItinerariesByCity;
import com.tomiok.tripselectorservice.usecase.itineraries.FindItinerariesByCityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FindItinerariesByCityConfig.class)
public class TripSelectorServiceConfig {

  @Bean
  public TripSelectorService tripSelectorService(final FindItinerariesByCity findItinerariesByCity) {
    return new TripSelectorServiceImpl(findItinerariesByCity);
  }
}
