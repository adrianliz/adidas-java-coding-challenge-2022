package com.adidas.backend.adiclubservice.domain;

import java.time.Instant;

public final class UserRegistrationDate {
  private final Instant value;

  public UserRegistrationDate(final Instant value) {
    this.value = value;
  }

  public Instant value() {
    return value;
  }
}
