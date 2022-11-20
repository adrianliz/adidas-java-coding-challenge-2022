package com.adidas.backend.prioritysaleservice.domain;

import java.util.Objects;

public final class AdiClubMember implements Comparable<AdiClubMember>, EmailIdentifiable {
  private final UserEmail email;
  private final AdiClubMemberRegistrationDate registrationDate;
  private final AdiClubMemberMembershipPoints membershipPoints;

  public AdiClubMember(
      final UserEmail email,
      final AdiClubMemberRegistrationDate registrationDate,
      final AdiClubMemberMembershipPoints membershipPoints) {

    validate(email, registrationDate, membershipPoints);
    this.email = email;
    this.registrationDate = registrationDate;
    this.membershipPoints = membershipPoints;
  }

  private void validate(
      final UserEmail email,
      final AdiClubMemberRegistrationDate registrationDate,
      final AdiClubMemberMembershipPoints membershipPoints) {

    if (email == null) {
      throw new IllegalArgumentException("Email cannot be null");
    }
    if (registrationDate == null) {
      throw new IllegalArgumentException("Registration date cannot be null");
    }
    if (membershipPoints == null) {
      throw new IllegalArgumentException("Membership points cannot be null");
    }
  }

  @Override
  public UserEmail getEmail() {
    return email;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AdiClubMember that = (AdiClubMember) o;
    return Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }

  @Override
  public int compareTo(final AdiClubMember o) {
    final int membershipPointsComparative = membershipPoints.compareTo(o.membershipPoints);

    return membershipPointsComparative == 0
        ? registrationDate.compareTo(o.registrationDate)
        : membershipPointsComparative;
  }
}
