package com.adidas.backend.prioritysaleservice.application;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;
import com.adidas.backend.prioritysaleservice.domain.SaleSubscriptions;
import com.adidas.backend.prioritysaleservice.domain.UnregisteredUser;
import org.springframework.stereotype.Service;

@Service
public final class AddSaleSubscriptionUseCase {
  private final SaleSubscriptions saleSubscriptions;

  public AddSaleSubscriptionUseCase(final SaleSubscriptions saleSubscriptions) {
    this.saleSubscriptions = saleSubscriptions;
  }

  public void addSubscription(final UnregisteredUser unregisteredUser) {
    saleSubscriptions.addUser(unregisteredUser);
  }

  public void addSubscription(final AdiClubMember adiClubMember) {
    saleSubscriptions.addUser(adiClubMember);
  }
}
