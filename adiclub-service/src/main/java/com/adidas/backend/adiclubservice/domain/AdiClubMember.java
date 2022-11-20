package com.adidas.backend.adiclubservice.domain;

public final class AdiClubMember {
  private final UserEmail email;
  private final UserRegistrationDate registrationDate;
  private final MembershipPoints membershipPoints;

  public AdiClubMember(
      final UserEmail email,
      final UserRegistrationDate registrationDate,
      final MembershipPoints membershipPoints) {
    this.email = email;
    this.registrationDate = registrationDate;
    this.membershipPoints = membershipPoints;
  }

  public UserEmail email() {
    return email;
  }

  public UserRegistrationDate registrationDate() {
    return registrationDate;
  }

  public MembershipPoints membershipPoints() {
    return membershipPoints;
  }
}
