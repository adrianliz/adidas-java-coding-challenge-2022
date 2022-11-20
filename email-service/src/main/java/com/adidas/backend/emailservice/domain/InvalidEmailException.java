package com.adidas.backend.emailservice.domain;

public final class InvalidEmailException extends RuntimeException {
  public InvalidEmailException(final String message) {
    super(message);
  }
}
