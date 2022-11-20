package com.adidas.backend.adiclubservice.infrastructure.persitence;

import com.adidas.backend.adiclubservice.domain.AdiClubMember;
import com.adidas.backend.adiclubservice.domain.AdiClubMemberRepository;
import com.adidas.backend.adiclubservice.domain.MembershipPoints;
import com.adidas.backend.adiclubservice.domain.UserEmail;
import com.adidas.backend.adiclubservice.domain.UserRegistrationDate;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Repository;

@Repository
public final class InMemoryAdiClubMemberRepository implements AdiClubMemberRepository {
  private static final Random RANDOM = new Random(System.nanoTime());

  private final List<UserEmail> REGISTERED_EMAILS;

  public InMemoryAdiClubMemberRepository() {
    REGISTERED_EMAILS =
        List.of(
            new UserEmail("uno@adidas.com"),
            new UserEmail("dos@adidas.com"),
            new UserEmail("tres@adidas.com"));
  }

  @Override
  public Optional<AdiClubMember> find(final UserEmail email) {
    return REGISTERED_EMAILS.stream()
        .filter(e -> e.equals(email))
        .findFirst()
        .map(
            e ->
                new AdiClubMember(
                    e,
                    new UserRegistrationDate(
                        Instant.now().minus(RANDOM.nextInt(365), ChronoUnit.DAYS)),
                    new MembershipPoints(RANDOM.nextInt(5000))));
  }
}
