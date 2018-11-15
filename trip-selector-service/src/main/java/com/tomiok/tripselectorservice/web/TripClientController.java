package com.tomiok.tripselectorservice.web;

import com.tomiok.tripselectorservice.clients.http.itineraries.ItineraryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itineraries-finder")
public class TripClientController {

  @Autowired
  private ItineraryProxy proxy;

  @GetMapping("/{city}")
  public ResponseEntity<?> findItinerariesByCities(@PathVariable String city) {

    return ResponseEntity.ok(proxy.doCall(city));
  }
}
