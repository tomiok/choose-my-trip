package com.tomiok.itineraryservice.web;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import com.tomiok.itineraryservice.adapters.city.CityService;
import com.tomiok.itineraryservice.usecase.city.CreateCity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {

  private final CityService cityService;

  public CityController(final CityService cityService) {
    this.cityService = cityService;
  }

  @PostMapping
  public ResponseEntity<CreateCity.CitySummary> createCity(@RequestBody CreateCityHttpRequest req) {
    CreateCity.CitySummary summary = cityService
        .createCity(new CreateCity.CreateCityCmd(req.getName(), req.getCode()));
    return status(HttpStatus.CREATED).body(summary);
  }

  @GetMapping
  public ResponseEntity<List<CreateCity.CitySummary>> findAll() {
    return ok(cityService.findAll());
  }
}
