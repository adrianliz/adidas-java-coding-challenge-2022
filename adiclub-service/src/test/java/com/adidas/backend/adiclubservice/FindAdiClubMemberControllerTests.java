package com.adidas.backend.adiclubservice;

import com.adidas.backend.adiclubservice.infrastructure.controller.FindAdiClubMemberController;
import com.adidas.backend.adiclubservice.infrastructure.controller.response.AdiClubMemberResponse;
import java.time.Instant;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = FindAdiClubMemberController.class)
class FindAdiClubMemberControllerTests {

  @Autowired private FindAdiClubMemberController findAdiClubMemberController;

  @ParameterizedTest
  @ValueSource(
      strings = {"user1@gmail.com", "user2@gmail.com", "user3@adiclub.com", "user4@adiclub.com"})
  void getAdiClubMemberInfoTest(final String email) {
    final ResponseEntity<AdiClubMemberResponse> response =
        findAdiClubMemberController.findAdiClubMember(email);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertInstanceOf(
        Integer.class, Objects.requireNonNull(response.getBody()).getPoints());
    Assertions.assertInstanceOf(Instant.class, response.getBody().getRegistrationDate());
    Assertions.assertEquals(email, response.getBody().getEmail());
  }
}
