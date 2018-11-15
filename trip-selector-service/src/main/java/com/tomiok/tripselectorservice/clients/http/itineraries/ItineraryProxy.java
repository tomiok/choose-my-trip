package com.tomiok.tripselectorservice.clients.http.itineraries;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "itineraries", fallback = ItineraryRequestFallback.class, url = "localhost:8080")
public interface ItineraryProxy {

  @RequestMapping(method = RequestMethod.GET, value = "/itineraries/{city}")
  List<ItineraryResponse> doCall(@PathVariable("city") String city);
}
