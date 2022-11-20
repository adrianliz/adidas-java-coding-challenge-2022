package com.adidas.backend.adiclubservice.domain;

public final class InvalidMembershipPointsException extends RuntimeException {
  public InvalidMembershipPointsException(final String message) {
    super(message);
  }
}
