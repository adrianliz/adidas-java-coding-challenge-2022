package com.adidas.backend.prioritysaleservice.domain;

import java.util.Collections;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import org.springframework.stereotype.Component;

@Component
public final class SaleSubscriptions {
  private final Queue<UnregisteredUser> unregisteredUserSubscriptions;
  private final Queue<AdiClubMember> adiClubMemberSubscriptions;

  public SaleSubscriptions() {
    unregisteredUserSubscriptions = new ConcurrentLinkedQueue<>();
    adiClubMemberSubscriptions = new PriorityBlockingQueue<>(11, Collections.reverseOrder());
  }

  public SaleSubscriptions(
      final Queue<UnregisteredUser> unregisteredUserSubscriptions,
      final Queue<AdiClubMember> adiClubMemberSubscriptions) {
    this.unregisteredUserSubscriptions = unregisteredUserSubscriptions;
    this.adiClubMemberSubscriptions = adiClubMemberSubscriptions;
  }

  public void addUser(final UnregisteredUser unregisteredUser) {
    if (unregisteredUser != null) {
      unregisteredUserSubscriptions.add(unregisteredUser);
    }
  }

  public void addUser(final AdiClubMember adiClubMember) {
    if (adiClubMember != null) {
      adiClubMemberSubscriptions.add(adiClubMember);
    }
  }

  public Optional<SaleAccess> generateNextAccess() {
    return adiClubMemberSubscriptions.isEmpty()
        ? Optional.ofNullable(unregisteredUserSubscriptions.poll()).map(SaleAccess::from)
        : Optional.ofNullable(adiClubMemberSubscriptions.poll()).map(SaleAccess::from);
  }
}
