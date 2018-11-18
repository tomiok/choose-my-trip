package com.tomiok.itineraryservice.usecase.gateways;

public class EmptyItinerariesException extends RuntimeException {

  EmptyItinerariesException(final String message) {
    super(message);
  }
}
