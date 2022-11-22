package com.adidas.backend.emailservice.application;

import com.adidas.backend.emailservice.domain.EmailNotification;
import com.adidas.backend.emailservice.domain.EmailNotificationRepository;
import com.adidas.backend.emailservice.domain.UserEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public final class NotifyUserUseCase {
  private final EmailNotificationRepository emailNotificationRepository;
  private final UserAlreadyNotifiedValidator userAlreadyNotifiedValidator;

  public NotifyUserUseCase(
      final EmailNotificationRepository emailNotificationRepository,
      final UserAlreadyNotifiedValidator userAlreadyNotifiedValidator) {
    this.emailNotificationRepository = emailNotificationRepository;
    this.userAlreadyNotifiedValidator = userAlreadyNotifiedValidator;
  }

  public void notifyUser(final UserEmail userEmail) {
    if (userAlreadyNotifiedValidator.isUserAlreadyNotified(userEmail)) {
      return;
    }

    log.info("Sending email to {}", userEmail.value());
    emailNotificationRepository.save(new EmailNotification(userEmail));
  }
}
