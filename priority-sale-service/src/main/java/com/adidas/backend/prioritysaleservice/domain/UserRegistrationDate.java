package com.adidas.backend.prioritysaleservice.domain;

import java.time.Instant;

public final class UserRegistrationDate implements Comparable<UserRegistrationDate> {
  private final Instant value;

  public UserRegistrationDate(final Instant value) {
    this.value = value;
  }

  @Override
  public int compareTo(final UserRegistrationDate o) {
    return value.compareTo(o.value);
  }
}
