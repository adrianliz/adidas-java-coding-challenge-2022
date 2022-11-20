package com.adidas.backend.adiclubservice.domain;

public final class InvalidEmailException extends RuntimeException {
  public InvalidEmailException(final String message) {
    super(message);
  }
}
