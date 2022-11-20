package com.adidas.backend.prioritysaleservice.domain;

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
}
