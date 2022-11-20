package com.adidas.backend.prioritysaleservice.application.response;

import com.adidas.backend.prioritysaleservice.domain.SaleAccess;

public final class SaleAccessResponse {
  private final String userEmail;

  public static SaleAccessResponse from(final SaleAccess saleAccess) {
    return new SaleAccessResponse(saleAccess.getUserEmail());
  }

  public SaleAccessResponse(final String userEmail) {
    this.userEmail = userEmail;
  }
}
