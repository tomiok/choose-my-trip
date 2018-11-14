package com.tomiok.itineraryservice.adapters.itinerary;

import com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary;
import com.tomiok.itineraryservice.usecase.itinerary.FindItineraryByCity;
import com.tomiok.itineraryservice.usecase.itinerary.ItineraryUseCasesConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ItineraryUseCasesConfig.class)
public class ItineraryServiceConfig {

  @Bean
  public ItineraryService itineraryService(
      final CreateItinerary createItinerary,
      final FindItineraryByCity findItineraryByCity) {
    return new ItineraryServiceImpl(createItinerary, findItineraryByCity);
  }
}
