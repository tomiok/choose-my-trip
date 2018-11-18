package com.tomiok.tripselectorservice.usecase.itineraries;

import static java.util.stream.Collectors.toList;

import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryProxy;
import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryResponse;
import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class FindItineraryByCityImpl implements FindItinerariesByCity {

  private static final int INITIAL_CAPACITY = 2;

  private final ItineraryProxy itineraryProxy;

  private static final Comparator<ItineraryResponse> STOPS_COMPARATOR =
      Comparator.comparingInt(ItineraryResponse::getNumOfStops);

  private static final Comparator<ItineraryResponse> TIME_COMPARATOR =
      Comparator.comparing(firstIt ->
          Duration.between(firstIt.getDepartureTime(), firstIt.getArrivalTime()));

  FindItineraryByCityImpl(final ItineraryProxy itineraryProxy) {
    this.itineraryProxy = itineraryProxy;
  }

  @Override
  public Map<SortedType, List<ItineraryResponse>> fetchAndOrderItineraries(final String cityIdentifier) {
    List<ItineraryResponse> itineraries = itineraryProxy.doCall(cityIdentifier);

    Map<SortedType, List<ItineraryResponse>> itinerariesByCriteria = new HashMap<>(INITIAL_CAPACITY);

    Order timeOrder = orderList(SortedType.BY_TIME, itineraries, TIME_COMPARATOR);
    Order stopsOrder = orderList(SortedType.BY_STOPS, itineraries, STOPS_COMPARATOR);

    itinerariesByCriteria.computeIfAbsent(timeOrder.getSortedType(), key -> timeOrder.getOrderedResponse());
    itinerariesByCriteria.computeIfAbsent(stopsOrder.getSortedType(), key -> stopsOrder.getOrderedResponse());

    return itinerariesByCriteria;
  }

  private Order orderList(
      SortedType sortedType, List<ItineraryResponse> res, Comparator<ItineraryResponse> comparator) {
    List<ItineraryResponse> itineraryResponses = orderList(res, comparator);
    return new Order(sortedType, itineraryResponses);
  }

  private List<ItineraryResponse> orderList(List<ItineraryResponse> res, Comparator<ItineraryResponse> comparator) {
    return res
        .stream()
        .sorted(comparator)
        .collect(toList());
  }

  @Getter
  @AllArgsConstructor
  private class Order {

    private SortedType sortedType;
    private List<ItineraryResponse> orderedResponse;
  }
}
