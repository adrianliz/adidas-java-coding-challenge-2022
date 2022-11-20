package application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.adidas.backend.prioritysaleservice.application.AddSaleSubscriptionUseCase;
import com.adidas.backend.prioritysaleservice.application.GenerateNextSaleAccessUseCase;
import com.adidas.backend.prioritysaleservice.application.response.SaleAccessResponse;
import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;
import com.adidas.backend.prioritysaleservice.domain.SaleAccess;
import com.adidas.backend.prioritysaleservice.domain.SaleSubscriptions;
import com.adidas.backend.prioritysaleservice.domain.UnregisteredUser;
import domain.AdiClubMemberMother;
import domain.AdiClubMemberRegistrationDateMother;
import domain.UnregisteredUserMother;
import domain.UserEmailMother;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public final class GenerateNextSaleAccessUseCaseShould {

  private void addSubscriptionsInRandomOrder(
      final AddSaleSubscriptionUseCase useCase,
      final Stream<UnregisteredUser> unregisteredUsers,
      final Stream<AdiClubMember> adiClubMembers) {

    unregisteredUsers
        .sorted((o1, o2) -> ThreadLocalRandom.current().nextInt(-1, 2))
        .forEach(useCase::addSubscription);

    adiClubMembers
        .sorted((o1, o2) -> ThreadLocalRandom.current().nextInt(-1, 2))
        .forEach(useCase::addSubscription);
  }

  @Test
  public void return_first_unregistered_member() {
    final var saleSubscriptions = new SaleSubscriptions();
    final var addSaleSubscriptionUseCase = new AddSaleSubscriptionUseCase(saleSubscriptions);
    final var generateNextSaleAccessUseCase = new GenerateNextSaleAccessUseCase(saleSubscriptions);

    final var firstUnregisteredUser = UnregisteredUserMother.random();
    final var secondUnregisteredUser = UnregisteredUserMother.random();
    addSubscriptionsInRandomOrder(
        addSaleSubscriptionUseCase,
        Stream.of(firstUnregisteredUser, secondUnregisteredUser),
        Stream.empty());

    final var expectedNextSaleAccess =
        Optional.of(SaleAccessResponse.from(SaleAccess.from(firstUnregisteredUser)));

    final var nextSaleAccess = generateNextSaleAccessUseCase.generateNextAccess();

    assertEquals(expectedNextSaleAccess, nextSaleAccess);
  }

  @Test
  public void return_adi_club_member_with_max_membership_points() {
    final var saleSubscriptions = new SaleSubscriptions();
    final var addSaleSubscriptionUseCase = new AddSaleSubscriptionUseCase(saleSubscriptions);
    final var generateNextSaleAccessUseCase = new GenerateNextSaleAccessUseCase(saleSubscriptions);

    final var adiClubMemberWithLessPoints =
        AdiClubMemberMother.AdiClubMemberBuilder.builder().withMembershipPoints(100).build();
    final var adiClubMemberWithMaxPoints =
        AdiClubMemberMother.AdiClubMemberBuilder.builder().withMembershipPoints(200).build();
    final var unregisteredUser = UnregisteredUserMother.random();
    addSubscriptionsInRandomOrder(
        addSaleSubscriptionUseCase,
        Stream.of(unregisteredUser),
        Stream.of(adiClubMemberWithLessPoints, adiClubMemberWithMaxPoints));

    final var expectedNextSaleAccess =
        Optional.of(SaleAccessResponse.from(SaleAccess.from(adiClubMemberWithMaxPoints)));

    final var nextSaleAccess = generateNextSaleAccessUseCase.generateNextAccess();

    assertEquals(expectedNextSaleAccess, nextSaleAccess);
  }

  @Test
  public void return_adi_club_member_with_first_registration_date() {
    final var saleSubscriptions = new SaleSubscriptions();
    final var addSaleSubscriptionUseCase = new AddSaleSubscriptionUseCase(saleSubscriptions);
    final var generateNextSaleAccessUseCase = new GenerateNextSaleAccessUseCase(saleSubscriptions);

    final var adiClubMemberRegisteredOneYearAgo =
        AdiClubMemberMother.AdiClubMemberBuilder.builder()
            .withUserEmail(UserEmailMother.adiclub())
            .withMembershipPoints(100)
            .withRegistrationDate(AdiClubMemberRegistrationDateMother.oneYearAgo())
            .build();
    final var adiClubMemberRegisteredTwoYearsAgo =
        AdiClubMemberMother.AdiClubMemberBuilder.builder()
            .withUserEmail(UserEmailMother.adiclub())
            .withMembershipPoints(100)
            .withRegistrationDate(AdiClubMemberRegistrationDateMother.twoYearsAgo())
            .build();
    final var unregisteredUser = UnregisteredUserMother.random();
    addSubscriptionsInRandomOrder(
        addSaleSubscriptionUseCase,
        Stream.of(unregisteredUser),
        Stream.of(adiClubMemberRegisteredOneYearAgo, adiClubMemberRegisteredTwoYearsAgo));

    final var expectedNextSaleAccess =
        Optional.of(SaleAccessResponse.from(SaleAccess.from(adiClubMemberRegisteredTwoYearsAgo)));

    final var nextSaleAccess = generateNextSaleAccessUseCase.generateNextAccess();

    assertEquals(expectedNextSaleAccess, nextSaleAccess);
  }

  @Test
  public void return_empty_when_no_subscriptions() {
    final var saleSubscriptions = new SaleSubscriptions();
    final var generateNextSaleAccessUseCase = new GenerateNextSaleAccessUseCase(saleSubscriptions);

    final var expectedNextSaleAccess = Optional.empty();

    final var nextSaleAccess = generateNextSaleAccessUseCase.generateNextAccess();

    assertEquals(expectedNextSaleAccess, nextSaleAccess);
  }

  @Test
  public void return_sale_accesses_in_order() {
    final var saleSubscriptions = new SaleSubscriptions();
    final var addSaleSubscriptionUseCase = new AddSaleSubscriptionUseCase(saleSubscriptions);
    final var generateNextSaleAccessUseCase = new GenerateNextSaleAccessUseCase(saleSubscriptions);

    final var adiClubMemberRegisteredOneYearAgo =
        AdiClubMemberMother.AdiClubMemberBuilder.builder()
            .withUserEmail(UserEmailMother.adiclub())
            .withMembershipPoints(100)
            .withRegistrationDate(AdiClubMemberRegistrationDateMother.oneYearAgo())
            .build();
    final var adiClubMemberRegisteredTwoYearsAgo =
        AdiClubMemberMother.AdiClubMemberBuilder.builder()
            .withUserEmail(UserEmailMother.adiclub())
            .withMembershipPoints(100)
            .withRegistrationDate(AdiClubMemberRegistrationDateMother.twoYearsAgo())
            .build();
    final var adiClubMemberWithMaxPoints =
        AdiClubMemberMother.AdiClubMemberBuilder.builder()
            .withUserEmail(UserEmailMother.adiclub())
            .withMembershipPoints(200)
            .withRegistrationDate(AdiClubMemberRegistrationDateMother.twoYearsAgo())
            .build();
    final var otherUnregisteredUser = UnregisteredUserMother.random();
    final var unregisteredUser = UnregisteredUserMother.random();
    addSubscriptionsInRandomOrder(
        addSaleSubscriptionUseCase,
        Stream.of(unregisteredUser, otherUnregisteredUser),
        Stream.of(
            adiClubMemberRegisteredOneYearAgo,
            adiClubMemberRegisteredTwoYearsAgo,
            adiClubMemberWithMaxPoints));

    assertEquals(
        Optional.of(SaleAccessResponse.from(SaleAccess.from(adiClubMemberWithMaxPoints))),
        generateNextSaleAccessUseCase.generateNextAccess());
    assertEquals(
        Optional.of(SaleAccessResponse.from(SaleAccess.from(adiClubMemberRegisteredTwoYearsAgo))),
        generateNextSaleAccessUseCase.generateNextAccess());
    assertEquals(
        Optional.of(SaleAccessResponse.from(SaleAccess.from(adiClubMemberRegisteredOneYearAgo))),
        generateNextSaleAccessUseCase.generateNextAccess());
    assertThat(generateNextSaleAccessUseCase.generateNextAccess())
        .isIn(
            Optional.of(SaleAccessResponse.from(SaleAccess.from(unregisteredUser))),
            Optional.of(SaleAccessResponse.from(SaleAccess.from(otherUnregisteredUser))));
    assertThat(generateNextSaleAccessUseCase.generateNextAccess())
        .isIn(
            Optional.of(SaleAccessResponse.from(SaleAccess.from(unregisteredUser))),
            Optional.of(SaleAccessResponse.from(SaleAccess.from(otherUnregisteredUser))));
    assertEquals(generateNextSaleAccessUseCase.generateNextAccess(), Optional.empty());
  }
}
