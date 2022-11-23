package com.adidas.backend.prioritysaleservice.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import org.springframework.stereotype.Component;

@Component
public final class SaleSubscriptions {
  private final Set<EmailIdentifiable> subscribers;
  private final Queue<UnregisteredUser> unregisteredUserSubscriptions;
  private final Queue<AdiClubMember> adiClubMemberSubscriptions;

  public SaleSubscriptions() {
    subscribers = Collections.synchronizedSet(new HashSet<>());
    unregisteredUserSubscriptions = new ConcurrentLinkedQueue<>();
    adiClubMemberSubscriptions = new PriorityBlockingQueue<>(11, Collections.reverseOrder());
  }

  public SaleSubscriptions(
      final Queue<UnregisteredUser> unregisteredUserSubscriptions,
      final Queue<AdiClubMember> adiClubMemberSubscriptions) {

    subscribers = Collections.synchronizedSet(new HashSet<>());
    this.unregisteredUserSubscriptions = unregisteredUserSubscriptions;
    this.adiClubMemberSubscriptions = adiClubMemberSubscriptions;
  }

  public void addUser(final UnregisteredUser unregisteredUser) {
    if (unregisteredUser != null && !subscribers.contains(unregisteredUser)) {
      unregisteredUserSubscriptions.add(unregisteredUser);
      subscribers.add(unregisteredUser);
    }
  }

  public void addUser(final AdiClubMember adiClubMember) {
    if (adiClubMember != null && !subscribers.contains(adiClubMember)) {
      adiClubMemberSubscriptions.add(adiClubMember);
      subscribers.add(adiClubMember);
    }
  }

  public Optional<SaleAccess> generateNextAccess() {
    return adiClubMemberSubscriptions.isEmpty()
        ? Optional.ofNullable(unregisteredUserSubscriptions.poll()).map(SaleAccess::from)
        : Optional.ofNullable(adiClubMemberSubscriptions.poll()).map(SaleAccess::from);
  }
}
