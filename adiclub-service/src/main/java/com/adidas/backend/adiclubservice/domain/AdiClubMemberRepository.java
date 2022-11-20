package com.adidas.backend.adiclubservice.domain;

import java.util.Optional;

public interface AdiClubMemberRepository {
  Optional<AdiClubMember> find(final AdiClubMemberUserEmail email);
}
