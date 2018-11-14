package com.tomiok.itineraryservice.usecase.itinerary;

import com.tomiok.itineraryservice.usecase.gateways.EntitiesGatewayConfig;
import com.tomiok.itineraryservice.usecase.gateways.ItineraryEntityGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(EntitiesGatewayConfig.class)
public class ItineraryUseCasesConfig {

  @Bean
  public FindItineraryByCity findItineraryByCity(final ItineraryEntityGateway itineraryEntityGateway) {
    return new FindItineraryByCityImpl(itineraryEntityGateway);
  }

  @Bean
  public CreateItinerary createItinerary(final ItineraryEntityGateway itineraryEntityGateway) {
    return new CreateItineraryImpl(itineraryEntityGateway);
  }
}
