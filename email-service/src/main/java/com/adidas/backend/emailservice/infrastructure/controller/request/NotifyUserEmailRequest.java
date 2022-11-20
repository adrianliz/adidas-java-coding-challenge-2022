package com.adidas.backend.emailservice.infrastructure.controller.request;

import com.adidas.backend.emailservice.domain.UserEmail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public final class NotifyUserEmailRequest {
  private final String userEmail;

  public UserEmail toUserEmail() {
    return new UserEmail(userEmail);
  }
}
