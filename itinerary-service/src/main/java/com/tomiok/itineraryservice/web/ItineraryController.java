package com.tomiok.itineraryservice.web;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import com.tomiok.itineraryservice.adapters.itinerary.ItineraryService;
import com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary;
import com.tomiok.itineraryservice.usecase.itinerary.CreateItinerary.ItinerarySummary;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {

  private final ItineraryService itineraryService;

  public ItineraryController(final ItineraryService itineraryService) {
    this.itineraryService = itineraryService;
  }

  @PostMapping
  public ResponseEntity<ItinerarySummary> createItinerary(
      @RequestBody @Valid CreateItineraryHttpReq req) {

    return status(HttpStatus.CREATED).body(itineraryService
        .createItinerary(new CreateItinerary.CreateItineraryCmd(
            req.getDepartureCity(),
            req.getDestinyCity(),
            req.getDepartureTime(),
            req.getArrivalTime(),
            req.getNumOfStops()
        )));
  }

  @GetMapping("/{city}")
  public ResponseEntity<List<ItinerarySummary>> findByCity(@PathVariable("city") String cityIdentifier) {
    return ok(itineraryService.findByCity(cityIdentifier));
  }
}
