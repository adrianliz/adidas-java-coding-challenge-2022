package com.adidas.backend.prioritysaleservice.domain;

public final class MembershipPoints implements Comparable<MembershipPoints> {
  private final Integer points;

  public MembershipPoints(final Integer points) {
    this.points = points;
  }

  @Override
  public int compareTo(final MembershipPoints o) {
    return points.compareTo(o.points);
  }
}
