package com.adidas.backend.adiclubservice.application.responses;

import com.adidas.backend.adiclubservice.domain.AdiClubMember;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.time.Instant;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
}
