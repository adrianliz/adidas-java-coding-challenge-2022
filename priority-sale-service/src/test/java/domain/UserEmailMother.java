package domain;

import com.adidas.backend.prioritysaleservice.domain.UserEmail;

public final class UserEmailMother {
  public static UserEmail random() {
    return new UserEmail(MotherCreator.random().internet().emailAddress());
  }

  public static UserEmail adiclub() {
    return new UserEmail(MotherCreator.random().name().username() + "@adidas.com");
  }
}
