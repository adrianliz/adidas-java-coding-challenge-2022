package com.adidas.backend.prioritysaleservice.domain;

public class UnregisteredUser implements EmailIdentifiable {
  private final UserEmail email;

  public UnregisteredUser(final UserEmail email) {
    this.email = email;
  }

  @Override
  public UserEmail getEmail() {
    return email;
  }
}
