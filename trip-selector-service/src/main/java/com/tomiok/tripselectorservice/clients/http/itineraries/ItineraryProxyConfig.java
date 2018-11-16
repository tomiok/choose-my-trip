package com.tomiok.tripselectorservice.clients.http.itineraries;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItineraryProxyConfig {

  @Bean
  public ItineraryRequestFallback itineraryResponse() {
    return new ItineraryRequestFallback();
  }
}
