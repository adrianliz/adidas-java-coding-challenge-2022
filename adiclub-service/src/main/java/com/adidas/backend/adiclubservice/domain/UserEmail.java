package com.adidas.backend.adiclubservice.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public final class UserEmail {
  private static final Pattern EMAIL_REGEXP =
      Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@adidas.com$");

  private final String value;

  public UserEmail(final String value) {
    validate(value);
    this.value = value;
  }

  private void validate(final String value) {
    if (value == null || value.isBlank() || !EMAIL_REGEXP.matcher(value).matches()) {
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
    final UserEmail userEmail = (UserEmail) o;
    return Objects.equals(value, userEmail.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
