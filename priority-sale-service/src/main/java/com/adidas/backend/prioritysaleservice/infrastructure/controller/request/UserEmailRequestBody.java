package com.adidas.backend.prioritysaleservice.infrastructure.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public final class UserEmailRequestBody {
  private String userEmail;
}
