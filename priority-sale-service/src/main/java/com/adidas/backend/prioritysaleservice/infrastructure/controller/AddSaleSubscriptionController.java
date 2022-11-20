package com.adidas.backend.prioritysaleservice.infrastructure.controller;

import com.adidas.backend.prioritysaleservice.application.AddSaleSubscriptionUseCase;
import com.adidas.backend.prioritysaleservice.domain.InvalidEmailException;
import com.adidas.backend.prioritysaleservice.domain.UnregisteredUser;
import com.adidas.backend.prioritysaleservice.domain.UserEmail;
import com.adidas.backend.prioritysaleservice.infrastructure.controller.request.UserEmailRequestBody;
import com.adidas.backend.prioritysaleservice.infrastructure.controller.response.SearchAdiClubMemberResponse;
import java.util.Objects;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/priority-sale", consumes = MediaType.APPLICATION_JSON_VALUE)
public final class AddSaleSubscriptionController {
  private static final String ADICLUB_SERVICE_ENDPOINT = "http://localhost:8080/adiclub/";
  private static final String ADICLUB_SEARCH_RESOURCE = "/search";

  private final AddSaleSubscriptionUseCase addSaleSubscriptionUseCase;
  private final RestTemplate webClient;

  public AddSaleSubscriptionController(
      final AddSaleSubscriptionUseCase addSaleSubscriptionUseCase) {
    this.addSaleSubscriptionUseCase = addSaleSubscriptionUseCase;
    webClient = new RestTemplateBuilder().rootUri(ADICLUB_SERVICE_ENDPOINT).build();
  }

  @PostMapping("/subscriptions")
  public void addSubscription(@RequestBody final UserEmailRequestBody body) {
    try {
      final UserEmail userEmail = new UserEmail(body.getUserEmail());

      try {
        final String path =
            UriComponentsBuilder.fromUriString(ADICLUB_SEARCH_RESOURCE)
                .queryParam("userEmail", body.getUserEmail())
                .build()
                .toUriString();

        final SearchAdiClubMemberResponse searchAdiClubMemberResponse =
            webClient.getForEntity(path, SearchAdiClubMemberResponse.class).getBody();

        addSaleSubscriptionUseCase.addSubscription(
            Objects.requireNonNull(searchAdiClubMemberResponse).toAdiClubMember());
      } catch (final RestClientException restClientException) {
        addSaleSubscriptionUseCase.addSubscription(new UnregisteredUser(userEmail));
      }
    } catch (final InvalidEmailException invalidEmailException) {
      throw new ServerWebInputException(invalidEmailException.getMessage());
    }
  }
}
