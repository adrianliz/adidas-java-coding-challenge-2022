package com.adidas.backend.prioritysaleservice.domain;

public final class SaleAccess {
  private final UserEmail userEmail;

  public static SaleAccess from(final EmailIdentifiable emailIdentifiable) {
    return new SaleAccess(emailIdentifiable.getEmail());
  }

  public SaleAccess(final UserEmail userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserEmail() {
    return userEmail.value();
  }
}
