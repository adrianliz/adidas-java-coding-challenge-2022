package com.adidas.backend.emailservice.infrastructure.persistence;

import com.adidas.backend.emailservice.domain.EmailNotification;
import com.adidas.backend.emailservice.domain.EmailNotificationRepository;
import com.adidas.backend.emailservice.domain.UserEmail;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public final class InMemoryEmailNotificationRepository implements EmailNotificationRepository {
  private final Set<EmailNotification> emailNotifications;

  public InMemoryEmailNotificationRepository() {
    emailNotifications = new HashSet<>();
  }

  @Override
  public void save(final EmailNotification emailNotification) {
    emailNotifications.add(emailNotification);
  }

  @Override
  public Optional<EmailNotification> find(final UserEmail userEmail) {
    return emailNotifications.stream().filter(n -> n.hasEmail(userEmail)).findFirst();
  }
}
