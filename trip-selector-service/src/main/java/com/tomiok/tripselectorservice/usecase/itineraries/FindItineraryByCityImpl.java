package com.tomiok.tripselectorservice.usecase.itineraries;

import static java.util.stream.Collectors.toList;

import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryProxy;
import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryResponse;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindItineraryByCityImpl implements FindItinerariesByCity {

  private final ItineraryProxy itineraryProxy;

  private static final Comparator<ItineraryResponse> STOPS_COMPARATOR =
      Comparator.comparingInt(ItineraryResponse::getNumOfStops);

  private static final Comparator<ItineraryResponse> TIME_COMPARATOR =
      (firstIt, secondIt) -> {
        long firstItDurationInMillis = firstIt.getDepartureTime().until(firstIt.getArrivalTime(), ChronoUnit.MILLIS);
        long secondItDurationInMillis = secondIt.getDepartureTime().until(secondIt.getArrivalTime(), ChronoUnit.MILLIS);

        return Long.compare(firstItDurationInMillis, secondItDurationInMillis);
      };

  FindItineraryByCityImpl(final ItineraryProxy itineraryProxy) {
    this.itineraryProxy = itineraryProxy;
  }

  @Override
  public Map<SortedType, List<ItineraryResponse>> fetchItineraries(final String cityIdentifier) {
    List<ItineraryResponse> itineraries = itineraryProxy.doCall(cityIdentifier);
    List<ItineraryResponse> itByTime = splitForTime(itineraries);
    List<ItineraryResponse> itByStops = splitForStops(itineraries);
    Map<SortedType, List<ItineraryResponse>> itinerariesByCriteria = new HashMap<>(2);
    itinerariesByCriteria.put(SortedType.BY_TIME, itByTime);
    itinerariesByCriteria.put(SortedType.BY_STOPS, itByStops);
    return itinerariesByCriteria;
  }

  private List<ItineraryResponse> splitForStops(List<ItineraryResponse> res) {
    return res
        .stream()
        .sorted(STOPS_COMPARATOR)
        .collect(toList());
  }

  private List<ItineraryResponse> splitForTime(List<ItineraryResponse> res) {
    return res
        .stream()
        .sorted(TIME_COMPARATOR)
        .collect(toList());
  }
}
