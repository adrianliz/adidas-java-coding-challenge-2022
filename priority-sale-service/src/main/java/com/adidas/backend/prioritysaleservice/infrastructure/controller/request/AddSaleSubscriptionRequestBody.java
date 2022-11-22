package com.adidas.backend.prioritysaleservice.infrastructure.controller.request;

import com.adidas.backend.prioritysaleservice.domain.UserEmail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "The request body to obtain access to the sale.")
public final class AddSaleSubscriptionRequestBody {
  @Schema(
      description = "The email of the user who wants to access to the sale.",
      required = true,
      example = "uno@adidas.es")
  private final String userEmail;

  public UserEmail getUserEmail() {
    return new UserEmail(userEmail);
  }
}
