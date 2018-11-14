package com.tomiok.itineraryservice.usecase.itinerary;

import com.tomiok.itineraryservice.model.Itinerary;
import java.util.List;

public interface FindItineraryByCity {

  List<Itinerary> findItinerariesByCity(String cityNameOrCode);
}
