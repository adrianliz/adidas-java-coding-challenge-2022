package com.adidas.backend.emailservice.domain;

public final class EmailNotification {
  private final UserEmail userEmail;

  public EmailNotification(final UserEmail userEmail) {
    validate(userEmail);
    this.userEmail = userEmail;
  }

  private void validate(final UserEmail userEmail) {
    if (userEmail == null) {
      throw new IllegalArgumentException("User email cannot be null");
    }
  }

  public UserEmail userEmail() {
    return userEmail;
  }

  public boolean hasEmail(final UserEmail userEmail) {
    return this.userEmail.equals(userEmail);
  }
}
