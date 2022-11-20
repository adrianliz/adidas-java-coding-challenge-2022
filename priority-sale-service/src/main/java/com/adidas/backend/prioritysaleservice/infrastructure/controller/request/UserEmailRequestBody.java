package com.adidas.backend.prioritysaleservice.infrastructure.controller.request;

import com.adidas.backend.prioritysaleservice.domain.UserEmail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public final class UserEmailRequestBody {
  private final String userEmail;

  public UserEmail toUserEmail() {
    return new UserEmail(userEmail);
  }
}
