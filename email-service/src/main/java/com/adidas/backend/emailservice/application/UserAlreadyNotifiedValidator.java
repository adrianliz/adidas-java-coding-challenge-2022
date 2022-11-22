package com.adidas.backend.emailservice.application;

import com.adidas.backend.emailservice.domain.EmailNotificationRepository;
import com.adidas.backend.emailservice.domain.UserEmail;
import org.springframework.stereotype.Service;

@Service
public final class UserAlreadyNotifiedValidator {
  private final EmailNotificationRepository emailNotificationRepository;

  public UserAlreadyNotifiedValidator(
      final EmailNotificationRepository emailNotificationRepository) {
    this.emailNotificationRepository = emailNotificationRepository;
  }

  public boolean isUserAlreadyNotified(final UserEmail userEmail) {
    return emailNotificationRepository.find(userEmail).isPresent();
  }
}
