package domain;

import com.adidas.backend.prioritysaleservice.domain.UnregisteredUser;

public final class UnregisteredUserMother {
  public static UnregisteredUser random() {
    return new UnregisteredUser(UserEmailMother.random());
  }
}
