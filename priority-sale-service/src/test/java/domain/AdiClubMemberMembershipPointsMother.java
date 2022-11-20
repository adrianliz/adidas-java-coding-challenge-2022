package domain;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberMembershipPoints;

public final class AdiClubMemberMembershipPointsMother {
  public static AdiClubMemberMembershipPoints random() {
    return new AdiClubMemberMembershipPoints(
        MotherCreator.random().number().numberBetween(0, 1000));
  }
}
