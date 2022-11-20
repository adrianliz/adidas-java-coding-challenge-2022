package domain;

import com.adidas.backend.prioritysaleservice.domain.MembershipPoints;

public final class MembershipPointsMother {
  public static MembershipPoints random() {
    return new MembershipPoints(MotherCreator.random().number().numberBetween(0, 1000));
  }
}
