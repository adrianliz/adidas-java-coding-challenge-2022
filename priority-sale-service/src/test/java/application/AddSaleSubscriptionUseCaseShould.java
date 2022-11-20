package application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.adidas.backend.prioritysaleservice.application.AddSaleSubscriptionUseCase;
import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;
import com.adidas.backend.prioritysaleservice.domain.SaleSubscriptions;
import com.adidas.backend.prioritysaleservice.domain.UnregisteredUser;
import domain.AdiClubMemberMother;
import domain.UnregisteredUserMother;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import org.junit.jupiter.api.Test;

public final class AddSaleSubscriptionUseCaseShould {
  private AddSaleSubscriptionUseCase addSaleSubscriptionUseCase;

  @Test
  public void add_unregistered_user_to_unregistered_user_subscriptions() {
    final Queue<UnregisteredUser> unregisteredUserSubscriptions = new ConcurrentLinkedQueue<>();
    final Queue<AdiClubMember> adiClubMemberSubscriptions = new PriorityBlockingQueue<>();

    final var saleSubscriptions =
        new SaleSubscriptions(unregisteredUserSubscriptions, adiClubMemberSubscriptions);
    final var addSaleSubscriptionUseCase = new AddSaleSubscriptionUseCase(saleSubscriptions);

    final var unregisteredUser = UnregisteredUserMother.random();
    addSaleSubscriptionUseCase.addSubscription(unregisteredUser);

    assertThat(unregisteredUserSubscriptions.contains(unregisteredUser)).isTrue();
    assertThat(adiClubMemberSubscriptions.isEmpty()).isTrue();
  }

  @Test
  public void add_adi_club_member_to_adi_club_member_subscriptions() {
    final Queue<UnregisteredUser> unregisteredUserSubscriptions = new ConcurrentLinkedQueue<>();
    final Queue<AdiClubMember> adiClubMemberSubscriptions = new PriorityBlockingQueue<>();

    final var saleSubscriptions =
        new SaleSubscriptions(unregisteredUserSubscriptions, adiClubMemberSubscriptions);
    final var addSaleSubscriptionUseCase = new AddSaleSubscriptionUseCase(saleSubscriptions);

    final var adiClubMember = AdiClubMemberMother.random();
    addSaleSubscriptionUseCase.addSubscription(adiClubMember);

    assertThat(adiClubMemberSubscriptions.contains(adiClubMember)).isTrue();
    assertThat(unregisteredUserSubscriptions.isEmpty()).isTrue();
  }
}
