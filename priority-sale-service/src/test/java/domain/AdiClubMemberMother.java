package domain;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;
import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberMembershipPoints;
import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberRegistrationDate;
import com.adidas.backend.prioritysaleservice.domain.UserEmail;
import java.time.Instant;

public final class AdiClubMemberMother {
  public static AdiClubMember random() {
    return new AdiClubMember(
        UserEmailMother.adiclub(),
        AdiClubMemberRegistrationDateMother.random(),
        AdiClubMemberMembershipPointsMother.random());
  }

  public static class AdiClubMemberBuilder {
    private UserEmail userEmail;
    private AdiClubMemberRegistrationDate registrationDate;
    private AdiClubMemberMembershipPoints membershipPoints;

    public static AdiClubMemberBuilder builder() {
      return new AdiClubMemberBuilder();
    }

    public AdiClubMemberBuilder withUserEmail(final String userEmail) {
      this.userEmail = new UserEmail(userEmail);
      return this;
    }

    public AdiClubMemberBuilder withUserEmail(final UserEmail userEmail) {
      this.userEmail = userEmail;
      return this;
    }

    public AdiClubMemberBuilder withRegistrationDate(final Instant registrationDate) {
      this.registrationDate = new AdiClubMemberRegistrationDate(registrationDate);
      return this;
    }

    public AdiClubMemberBuilder withRegistrationDate(
        final AdiClubMemberRegistrationDate registrationDate) {
      this.registrationDate = registrationDate;
      return this;
    }

    public AdiClubMemberBuilder withMembershipPoints(final Integer membershipPoints) {
      this.membershipPoints = new AdiClubMemberMembershipPoints(membershipPoints);
      return this;
    }

    public AdiClubMember build() {
      return new AdiClubMember(userEmail, registrationDate, membershipPoints);
    }
  }
}
