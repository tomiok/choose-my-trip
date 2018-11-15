package com.tomiok.itineraryservice.web;

import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CreateItineraryHttpReq {

  @NotEmpty
  @NotNull
  private String departureCity;

  @NotEmpty
  @NotNull
  private String destinyCity;

  @NotNull
  private LocalDateTime departureTime;

  @NotNull
  private LocalDateTime arrivalTime;

  @Min(0)
  private int numOfStops;
}
