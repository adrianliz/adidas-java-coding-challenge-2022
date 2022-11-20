package com.adidas.backend.emailservice.application;

import com.adidas.backend.emailservice.application.response.UserEmailAlreadyNotifiedResponse;
import com.adidas.backend.emailservice.domain.EmailNotificationRepository;
import com.adidas.backend.emailservice.domain.UserEmail;
import org.springframework.stereotype.Service;

@Service
public final class UserEmailAlreadyNotifiedUseCase {
  private final EmailNotificationRepository emailNotificationRepository;

  public UserEmailAlreadyNotifiedUseCase(
      final EmailNotificationRepository emailNotificationRepository) {
    this.emailNotificationRepository = emailNotificationRepository;
  }

  public UserEmailAlreadyNotifiedResponse isUserEmailAlreadyNotified(final UserEmail userEmail) {
    return emailNotificationRepository
        .find(userEmail)
        .map(UserEmailAlreadyNotifiedResponse::notified)
        .orElseGet(() -> UserEmailAlreadyNotifiedResponse.notNotified(userEmail));
  }
}
