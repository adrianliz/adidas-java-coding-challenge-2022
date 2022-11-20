package com.adidas.backend.prioritysaleservice.application.response;

import com.adidas.backend.prioritysaleservice.domain.SaleAccess;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class SaleAccessResponse {
  private final String userEmail;

  public static SaleAccessResponse from(final SaleAccess saleAccess) {
    return new SaleAccessResponse(saleAccess.getUserEmail());
  }

  public SaleAccessResponse(final String userEmail) {
    this.userEmail = userEmail;
  }

  public String userEmail() {
    return userEmail;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SaleAccessResponse that = (SaleAccessResponse) o;
    return Objects.equals(userEmail, that.userEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userEmail);
  }
}
