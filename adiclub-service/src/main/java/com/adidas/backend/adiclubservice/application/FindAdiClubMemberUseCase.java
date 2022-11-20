package com.adidas.backend.adiclubservice.application;

import com.adidas.backend.adiclubservice.application.responses.AdiClubMemberResponse;
import com.adidas.backend.adiclubservice.domain.AdiClubMemberNotFoundException;
import com.adidas.backend.adiclubservice.domain.AdiClubMemberRepository;
import com.adidas.backend.adiclubservice.domain.AdiClubMemberUserEmail;
import org.springframework.stereotype.Service;

@Service
public final class FindAdiClubMemberUseCase {
  private final AdiClubMemberRepository repository;

  public FindAdiClubMemberUseCase(final AdiClubMemberRepository repository) {
    this.repository = repository;
  }

  public AdiClubMemberResponse findAdiClubMember(final AdiClubMemberUserEmail userEmail) {
    return repository
        .find(userEmail)
        .map(AdiClubMemberResponse::fromAggregate)
        .orElseThrow(() -> new AdiClubMemberNotFoundException(userEmail));
  }
}
