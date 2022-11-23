package com.adidas.backend.prioritysaleservice.domain;

import java.util.Objects;

public final class SaleAccess {
  private final UserEmail userEmail;

  public static SaleAccess from(final EmailIdentifiable emailIdentifiable) {
    return new SaleAccess(emailIdentifiable.email());
  }

  public SaleAccess(final UserEmail userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserEmail() {
    return userEmail.value();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SaleAccess that = (SaleAccess) o;
    return Objects.equals(userEmail, that.userEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userEmail);
  }
}
