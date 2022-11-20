package com.adidas.backend.prioritysaleservice.infrastructure.controller.response;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;
import com.adidas.backend.prioritysaleservice.domain.MembershipPoints;
import com.adidas.backend.prioritysaleservice.domain.UnregisteredUser;
import com.adidas.backend.prioritysaleservice.domain.UserEmail;
import com.adidas.backend.prioritysaleservice.domain.UserRegistrationDate;
import java.time.Instant;
import lombok.Data;

@Data
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

  public UnregisteredUser toUnregisteredUser() {
    return new UnregisteredUser(new UserEmail(userEmail));
  }
}
