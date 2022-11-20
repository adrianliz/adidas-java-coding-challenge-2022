package com.adidas.backend.adiclubservice.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public final class AdiClubMemberUserEmail {
  private static final Pattern SIMPLE_EMAIL_REGEXP = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

  private final String value;

  public AdiClubMemberUserEmail(final String value) {
    validate(value);
    this.value = value;
  }

  private void validate(final String value) {
    if (value == null || value.isBlank() || !SIMPLE_EMAIL_REGEXP.matcher(value).matches()) {
      throw new InvalidEmailException(String.format("Email <%s> is invalid", value));
    }
  }

  public String value() {
    return value;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final AdiClubMemberUserEmail userEmail = (AdiClubMemberUserEmail) o;
    return Objects.equals(value, userEmail.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
