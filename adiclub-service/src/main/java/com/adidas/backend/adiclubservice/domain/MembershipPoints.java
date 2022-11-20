package com.adidas.backend.adiclubservice.domain;

public final class MembershipPoints {
  private final Integer value;

  public MembershipPoints(final Integer value) {
    this.value = value;
  }

  public Integer value() {
    return value;
  }
}
