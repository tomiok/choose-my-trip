package com.tomiok.itineraryservice.usecase.city;

import com.tomiok.itineraryservice.usecase.gateways.CityEntityGateway;
import com.tomiok.itineraryservice.usecase.gateways.EntitiesGatewayConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(EntitiesGatewayConfig.class)
public class CityUseCasesConfig {

  @Bean
  public CreateCity createCity(final CityEntityGateway cityEntityGateway) {
    return new CreateCityImpl(cityEntityGateway);
  }

  @Bean
  public FindCity findCity(final CityEntityGateway cityEntityGateway) {
    return new FindCityImpl(cityEntityGateway);
  }
}
