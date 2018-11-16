package com.tomiok.tripselectorservice.clients.http.itineraries;

import static java.util.Collections.emptyList;

import java.util.List;

/**
 * Dummy fallback when <b>Itinerary-service</b> is down or the circuit is open
 */
public class ItineraryRequestFallback implements ItineraryProxy {

  @Override
  public List<ItineraryResponse> doCall(final String url) {
    return emptyList();
  }
}
