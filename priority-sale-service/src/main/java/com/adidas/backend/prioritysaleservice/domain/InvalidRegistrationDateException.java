package com.adidas.backend.prioritysaleservice.domain;

public final class InvalidRegistrationDateException extends RuntimeException {
  public InvalidRegistrationDateException(final String message) {
    super(message);
  }
}
