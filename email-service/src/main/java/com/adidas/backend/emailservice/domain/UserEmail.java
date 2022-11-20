package com.adidas.backend.emailservice.domain;

import java.util.Objects;

public final class UserEmail {
  private final String value;

  public UserEmail(final String value) {
    validate(value);
    this.value = value;
  }

  private void validate(final String value) {
    if (value == null || value.isBlank()) {
      throw new InvalidEmailException("Email is required");
    }
  }

  public String value() {
    return value;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final UserEmail userEmail = (UserEmail) o;
    return Objects.equals(value, userEmail.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
