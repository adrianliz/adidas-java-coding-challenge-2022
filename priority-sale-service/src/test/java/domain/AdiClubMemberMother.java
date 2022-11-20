package domain;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;
import com.adidas.backend.prioritysaleservice.domain.MembershipPoints;

public final class AdiClubMemberMother {
  public static AdiClubMember random() {
    return new AdiClubMember(
        UserEmailMother.adiclub(),
        UserRegistrationDateMother.random(),
        MembershipPointsMother.random());
  }

  public static AdiClubMember withPoints(final int points) {
    return new AdiClubMember(
        UserEmailMother.adiclub(),
        UserRegistrationDateMother.random(),
        new MembershipPoints(points));
  }
}
