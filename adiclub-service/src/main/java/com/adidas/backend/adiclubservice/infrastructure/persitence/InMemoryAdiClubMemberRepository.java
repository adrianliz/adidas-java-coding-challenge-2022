package com.adidas.backend.adiclubservice.infrastructure.persitence;

import com.adidas.backend.adiclubservice.domain.AdiClubMember;
import com.adidas.backend.adiclubservice.domain.AdiClubMemberMembershipPoints;
import com.adidas.backend.adiclubservice.domain.AdiClubMemberRegistrationDate;
import com.adidas.backend.adiclubservice.domain.AdiClubMemberRepository;
import com.adidas.backend.adiclubservice.domain.AdiClubMemberUserEmail;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Repository;

@Repository
public final class InMemoryAdiClubMemberRepository implements AdiClubMemberRepository {
  private static final Random RANDOM = new Random(System.nanoTime());

  private final List<AdiClubMemberUserEmail> registeredEmails;

  public InMemoryAdiClubMemberRepository() {
    registeredEmails =
        List.of(
            new AdiClubMemberUserEmail("uno@adidas.es"),
            new AdiClubMemberUserEmail("dos@adidas.es"),
            new AdiClubMemberUserEmail("tres@adidas.es"));
  }

  @Override
  public Optional<AdiClubMember> find(final AdiClubMemberUserEmail email) {
    return registeredEmails.stream()
        .filter(e -> e.equals(email))
        .findFirst()
        .map(
            e ->
                new AdiClubMember(
                    e,
                    new AdiClubMemberRegistrationDate(
                        Instant.now().minus(RANDOM.nextInt(365), ChronoUnit.DAYS)),
                    new AdiClubMemberMembershipPoints(RANDOM.nextInt(5000))));
  }
}
