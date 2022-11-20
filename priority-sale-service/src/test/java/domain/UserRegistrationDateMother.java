package domain;

import com.adidas.backend.prioritysaleservice.domain.UserRegistrationDate;

public final class UserRegistrationDateMother {
  public static UserRegistrationDate random() {
    return new UserRegistrationDate(MotherCreator.random().date().birthday().toInstant());
  }
}
