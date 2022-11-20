package com.adidas.backend.prioritysaleservice.domain;

public final class InvalidMembershipPointsException extends RuntimeException {
  public InvalidMembershipPointsException(final String message) {
    super(message);
  }
}
