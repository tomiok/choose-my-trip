package com.tomiok.itineraryservice.adapters.city;

import com.tomiok.itineraryservice.usecase.city.CityUseCasesConfig;
import com.tomiok.itineraryservice.usecase.city.CreateCity;
import com.tomiok.itineraryservice.usecase.city.FindCity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CityUseCasesConfig.class)
public class CityServiceConfig {

  @Bean
  public CityService cityService(final FindCity findCity, final CreateCity createCity) {
    return new CityServiceImpl(findCity, createCity);
  }
}
