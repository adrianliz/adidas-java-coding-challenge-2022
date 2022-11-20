package com.adidas.backend.prioritysaleservice.domain;

import java.util.Objects;

public final class AdiClubMemberMembershipPoints
    implements Comparable<AdiClubMemberMembershipPoints> {

  private final Integer value;

  public AdiClubMemberMembershipPoints(final Integer value) {
    validate(value);
    this.value = value;
  }

  private void validate(final Integer points) {
    if (points == null || points < 0) {
      throw new InvalidMembershipPointsException("Membership points cannot be negative");
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AdiClubMemberMembershipPoints that = (AdiClubMemberMembershipPoints) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public int compareTo(final AdiClubMemberMembershipPoints o) {
    return value.compareTo(o.value);
  }
}
