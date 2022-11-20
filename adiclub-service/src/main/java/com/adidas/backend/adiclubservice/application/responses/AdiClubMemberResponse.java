package com.adidas.backend.adiclubservice.application.responses;

import com.adidas.backend.adiclubservice.domain.AdiClubMember;
import java.time.Instant;

public final class AdiClubMemberResponse {
  private final String userEmail;
  private final Instant registrationDate;
  private final Integer membershipPoints;

  public AdiClubMemberResponse(
      final String userEmail, final Instant registrationDate, final Integer membershipPoints) {
    this.userEmail = userEmail;
    this.registrationDate = registrationDate;
    this.membershipPoints = membershipPoints;
  }

  public static AdiClubMemberResponse fromAggregate(final AdiClubMember adiClubMember) {
    return new AdiClubMemberResponse(
        adiClubMember.email().value(),
        adiClubMember.registrationDate().value(),
        adiClubMember.membershipPoints().value());
  }

  public String getUserEmail() {
    return userEmail;
  }

  public Instant getRegistrationDate() {
    return registrationDate;
  }

  public Integer getMembershipPoints() {
    return membershipPoints;
  }
}
