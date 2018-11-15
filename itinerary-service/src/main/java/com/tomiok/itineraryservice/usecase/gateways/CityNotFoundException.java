package com.tomiok.itineraryservice.usecase.gateways;

public class CityNotFoundException extends RuntimeException {

  private String message;

  public CityNotFoundException(final String message) {
    this.message = message;
  }
}
