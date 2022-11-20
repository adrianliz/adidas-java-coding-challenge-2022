package com.adidas.backend.prioritysaleservice.infrastructure.webclient.request;

import com.adidas.backend.prioritysaleservice.application.response.SaleAccessResponse;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class NotifyUserEmailRequest {
  private final String userEmail;

  public NotifyUserEmailRequest(final String userEmail) {
    this.userEmail = userEmail;
  }

  public static NotifyUserEmailRequest from(final SaleAccessResponse saleAccessResponse) {
    return new NotifyUserEmailRequest(saleAccessResponse.userEmail());
  }
}
