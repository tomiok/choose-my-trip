package com.tomiok.tripselectorservice.clients.http.itineraries;

import static java.util.Collections.emptyList;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ItineraryRequestFallback implements ItineraryProxy {

  @Override
  public List<ItineraryResponse> doCall(final String url) {
    return emptyList();
  }
}
