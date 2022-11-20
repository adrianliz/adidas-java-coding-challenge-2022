package domain;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMemberRegistrationDate;
import java.time.temporal.ChronoUnit;

public final class AdiClubMemberRegistrationDateMother {
  public static AdiClubMemberRegistrationDate random() {
    return new AdiClubMemberRegistrationDate(MotherCreator.random().date().birthday().toInstant());
  }

  public static AdiClubMemberRegistrationDate oneYearAgo() {
    return new AdiClubMemberRegistrationDate(
        MotherCreator.random().date().birthday().toInstant().minus(365, ChronoUnit.DAYS));
  }

  public static AdiClubMemberRegistrationDate twoYearsAgo() {
    return new AdiClubMemberRegistrationDate(
        MotherCreator.random().date().birthday().toInstant().minus(365 * 2, ChronoUnit.DAYS));
  }
}
