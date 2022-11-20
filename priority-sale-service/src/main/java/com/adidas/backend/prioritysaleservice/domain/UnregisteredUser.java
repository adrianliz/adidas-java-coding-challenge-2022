package com.adidas.backend.prioritysaleservice.domain;

import java.util.Objects;

public class UnregisteredUser implements EmailIdentifiable {
  private final UserEmail email;

  public UnregisteredUser(final UserEmail email) {
    validate(email);
    this.email = email;
  }

  private void validate(final UserEmail email) {
    if (email == null) {
      throw new IllegalArgumentException("Email cannot be null");
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final UnregisteredUser that = (UnregisteredUser) o;
    return Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }

  @Override
  public UserEmail getEmail() {
    return email;
  }
}
