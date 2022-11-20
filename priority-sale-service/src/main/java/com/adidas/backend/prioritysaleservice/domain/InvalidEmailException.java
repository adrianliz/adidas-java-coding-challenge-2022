package com.adidas.backend.prioritysaleservice.domain;

public final class InvalidEmailException extends RuntimeException {
  public InvalidEmailException(final String message) {
    super(message);
  }
}
