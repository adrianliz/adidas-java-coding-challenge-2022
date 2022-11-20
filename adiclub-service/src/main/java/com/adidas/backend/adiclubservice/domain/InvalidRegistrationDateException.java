package com.adidas.backend.adiclubservice.domain;

public final class InvalidRegistrationDateException extends RuntimeException {
  public InvalidRegistrationDateException(final String message) {
    super(message);
  }
}
