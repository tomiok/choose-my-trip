package com.tomiok.tripselectorservice.web;

import com.tomiok.tripselectorservice.adapters.tripselector.TripSelectorService;
import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryResponse;
import com.tomiok.tripselectorservice.usecase.itineraries.SortedType;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itineraries-finder")
public class TripClientController {

  private final TripSelectorService tripSelectorService;

  public TripClientController(final TripSelectorService tripSelectorService) {
    this.tripSelectorService = tripSelectorService;
  }

  @GetMapping("/{city}")
  public ResponseEntity<Map<SortedType, List<ItineraryResponse>>> findItinerariesByCities(@PathVariable String city) {
    return ResponseEntity.ok(tripSelectorService.fetchItinerariesByCity(city));
  }
}
