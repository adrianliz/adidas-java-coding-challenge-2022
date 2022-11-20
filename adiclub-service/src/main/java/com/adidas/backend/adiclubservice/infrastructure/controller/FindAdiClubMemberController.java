package com.adidas.backend.adiclubservice.infrastructure.controller;

import com.adidas.backend.adiclubservice.application.FindAdiClubMemberUseCase;
import com.adidas.backend.adiclubservice.application.responses.AdiClubMemberResponse;
import com.adidas.backend.adiclubservice.domain.AdiClubMemberNotFoundException;
import com.adidas.backend.adiclubservice.domain.InvalidEmailException;
import com.adidas.backend.adiclubservice.domain.UserEmail;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

@RestController
@RequestMapping(value = "/adiclub", produces = MediaType.APPLICATION_JSON_VALUE)
public class FindAdiClubMemberController {
  private final FindAdiClubMemberUseCase findAdiClubMemberUseCase;

  public FindAdiClubMemberController(final FindAdiClubMemberUseCase findAdiClubMemberUseCase) {
    this.findAdiClubMemberUseCase = findAdiClubMemberUseCase;
  }

  @GetMapping("/search")
  public ResponseEntity<AdiClubMemberResponse> getAdiClubMemberInfo(
      @RequestParam(value = "userEmail") final String rawUserEmail) {

    try {
      final UserEmail userEmail = new UserEmail(rawUserEmail);
      return ResponseEntity.ok(findAdiClubMemberUseCase.findAdiClubMember(userEmail));
    } catch (final InvalidEmailException invalidEmailException) {
      throw new ServerWebInputException(invalidEmailException.getMessage());
    } catch (final AdiClubMemberNotFoundException adiClubMemberNotFoundException) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, adiClubMemberNotFoundException.getMessage());
    }
  }
}
