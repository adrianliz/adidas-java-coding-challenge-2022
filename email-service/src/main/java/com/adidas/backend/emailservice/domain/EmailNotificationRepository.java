package com.adidas.backend.emailservice.domain;

import java.util.Optional;

public interface EmailNotificationRepository {
  void save(EmailNotification emailNotification);

  Optional<EmailNotification> find(UserEmail userEmail);
}
