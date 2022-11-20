package com.adidas.backend.adiclubservice.domain;

public final class AdiClubMemberNotFoundException extends RuntimeException {
  public AdiClubMemberNotFoundException(final AdiClubMemberUserEmail userEmail) {
    super(String.format("adiClub member with email <%s> not found", userEmail.value()));
  }
}
