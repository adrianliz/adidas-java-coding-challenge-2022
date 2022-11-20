package com.adidas.backend.prioritysaleservice.infrastructure.webclient.response;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;
import com.adidas.backend.prioritysaleservice.domain.MembershipPoints;
import com.adidas.backend.prioritysaleservice.domain.UserEmail;
import com.adidas.backend.prioritysaleservice.domain.UserRegistrationDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SearchAdiClubMemberResponse {
  private String userEmail;
  private Instant registrationDate;
  private Integer membershipPoints;

  public AdiClubMember toAdiClubMember() {
    return new AdiClubMember(
        new UserEmail(userEmail),
        new UserRegistrationDate(registrationDate),
        new MembershipPoints(membershipPoints));
  }
}
