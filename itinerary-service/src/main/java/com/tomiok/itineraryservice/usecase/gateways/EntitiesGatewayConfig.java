package com.tomiok.itineraryservice.usecase.gateways;

import com.tomiok.itineraryservice.model.CityRepository;
import com.tomiok.itineraryservice.model.ItineraryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntitiesGatewayConfig {

  @Bean
  public CityEntityGateway cityEntityGateway(final CityRepository cityRepository) {
    return new CityEntityGatewayImpl(cityRepository);
  }

  @Bean
  public ItineraryEntityGateway itineraryEntityGateway(
      final CityEntityGateway cityEntityGateway,
      final ItineraryRepository itineraryRepository) {
    return new ItineraryEntityGatewayImpl(itineraryRepository, cityEntityGateway);
  }
}
