package com.adidas.backend.prioritysaleservice.domain;

public final class AdiClubMember implements Comparable<AdiClubMember>, EmailIdentifiable {
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

  @Override
  public UserEmail getEmail() {
    return email;
  }

  @Override
  public int compareTo(final AdiClubMember o) {
    final int membershipPointsComparative = membershipPoints.compareTo(o.membershipPoints);

    return membershipPointsComparative == 0
        ? registrationDate.compareTo(o.registrationDate)
        : membershipPointsComparative;
  }
}
