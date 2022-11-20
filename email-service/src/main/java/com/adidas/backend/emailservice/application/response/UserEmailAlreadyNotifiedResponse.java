package com.adidas.backend.emailservice.application.response;

import com.adidas.backend.emailservice.domain.EmailNotification;
import com.adidas.backend.emailservice.domain.UserEmail;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class UserEmailAlreadyNotifiedResponse {
  private final String userEmail;
  private final boolean notified;

  public UserEmailAlreadyNotifiedResponse(final String userEmail, final boolean notified) {
    this.userEmail = userEmail;
    this.notified = notified;
  }

  public static UserEmailAlreadyNotifiedResponse notified(
      final EmailNotification emailNotification) {
    return new UserEmailAlreadyNotifiedResponse(emailNotification.userEmail().value(), true);
  }

  public static UserEmailAlreadyNotifiedResponse notNotified(final UserEmail userEmail) {
    return new UserEmailAlreadyNotifiedResponse(userEmail.value(), false);
  }

  public boolean userAlreadyNotified() {
    return notified;
  }
}
