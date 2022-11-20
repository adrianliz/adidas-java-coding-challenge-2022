package com.adidas.backend.emailservice.application;

import com.adidas.backend.emailservice.domain.EmailNotification;
import com.adidas.backend.emailservice.domain.EmailNotificationRepository;
import com.adidas.backend.emailservice.domain.UserEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public final class NotifyEmailUseCase {
  private final EmailNotificationRepository emailNotificationRepository;
  private final UserEmailAlreadyNotifiedUseCase userEmailAlreadyNotifiedUseCase;

  public NotifyEmailUseCase(
      final EmailNotificationRepository emailNotificationRepository,
      final UserEmailAlreadyNotifiedUseCase userEmailAlreadyNotifiedUseCase) {
    this.emailNotificationRepository = emailNotificationRepository;
    this.userEmailAlreadyNotifiedUseCase = userEmailAlreadyNotifiedUseCase;
  }

  public void notifyUserEmail(final UserEmail userEmail) {
    if (userEmailAlreadyNotifiedUseCase
        .isUserEmailAlreadyNotified(userEmail)
        .userAlreadyNotified()) {
      return;
    }

    log.info("Sending email to {}", userEmail.value());
    emailNotificationRepository.save(new EmailNotification(userEmail));
  }
}
