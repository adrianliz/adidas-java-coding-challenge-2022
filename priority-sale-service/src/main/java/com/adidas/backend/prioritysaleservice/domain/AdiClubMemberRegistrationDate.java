package com.adidas.backend.prioritysaleservice.domain;

import java.time.Instant;
import java.util.Objects;

public final class AdiClubMemberRegistrationDate
    implements Comparable<AdiClubMemberRegistrationDate> {
  private final Instant value;

  public AdiClubMemberRegistrationDate(final Instant value) {
    validate(value);
    this.value = value;
  }

  private void validate(final Instant value) {
    if (value == null || value.isAfter(Instant.now())) {
      throw new InvalidRegistrationDateException("Registration date cannot be after now");
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
    final AdiClubMemberRegistrationDate that = (AdiClubMemberRegistrationDate) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public int compareTo(final AdiClubMemberRegistrationDate o) {
    return value.compareTo(o.value);
  }
}
