package domain;

import com.adidas.backend.prioritysaleservice.domain.UserEmail;
import java.util.List;
import java.util.function.Function;

public final class UserEmailMother {
  private static UserEmail generateEmailUntilDifferent(
      final List<UserEmail> userEmails, final Function<Void, UserEmail> generator) {

    UserEmail randomUserEmail;
    do {
      randomUserEmail = generator.apply(null);
    } while (userEmails.contains(randomUserEmail));
    return randomUserEmail;
  }

  public static UserEmail random() {
    return new UserEmail(MotherCreator.random().internet().emailAddress());
  }

  public static UserEmail randomAndDifferentFrom(final UserEmail userEmail) {
    return generateEmailUntilDifferent(List.of(userEmail), v -> random());
  }

  public static UserEmail adiclub() {
    return new UserEmail(MotherCreator.random().elderScrolls().firstName() + "@adidas.es");
  }

  public static UserEmail adiclubAndDifferentFrom(final UserEmail userEmail) {
    return generateEmailUntilDifferent(List.of(userEmail), v -> adiclub());
  }

  public static UserEmail adiclubAndDifferentFrom(final List<UserEmail> userEmails) {
    return generateEmailUntilDifferent(userEmails, v -> adiclub());
  }
}
