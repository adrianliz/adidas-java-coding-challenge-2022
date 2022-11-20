package com.adidas.backend.prioritysaleservice.infrastructure.controller;

import com.adidas.backend.prioritysaleservice.application.AddSaleSubscriptionUseCase;
import com.adidas.backend.prioritysaleservice.domain.InvalidEmailException;
import com.adidas.backend.prioritysaleservice.domain.UnregisteredUser;
import com.adidas.backend.prioritysaleservice.infrastructure.controller.request.UserEmailRequestBody;
import com.adidas.backend.prioritysaleservice.infrastructure.webclient.response.SearchAdiClubMemberResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/priority-sale", consumes = MediaType.APPLICATION_JSON_VALUE)
public final class AddSaleSubscriptionController {

  private final AddSaleSubscriptionUseCase addSaleSubscriptionUseCase;
  private final WebClient adiclubWebClient;
  private final String adiclubSearchEndpoint;

  public AddSaleSubscriptionController(
      final AddSaleSubscriptionUseCase addSaleSubscriptionUseCase,
      final WebClient adiclubWebClient,
      @Value("${adiclub.search.endpoint}") final String adiclubSearchEndpoint) {
    this.addSaleSubscriptionUseCase = addSaleSubscriptionUseCase;
    this.adiclubWebClient = adiclubWebClient;
    this.adiclubSearchEndpoint = adiclubSearchEndpoint;
  }

  @PostMapping("/subscriptions")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> addSubscription(@RequestBody final UserEmailRequestBody userEmailRequestBody) {
    return Mono.fromCallable(userEmailRequestBody::toUserEmail)
        .onErrorMap(
            InvalidEmailException.class,
            e -> new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage()))
        .flatMap(
            userEmail ->
                adiclubWebClient
                    .get()
                    .uri(
                        uriBuilder ->
                            uriBuilder
                                .path(adiclubSearchEndpoint)
                                .queryParam("userEmail", userEmail.value())
                                .build())
                    .retrieve()
                    .bodyToMono(SearchAdiClubMemberResponse.class)
                    .mapNotNull(
                        searchAdiClubMemberResponse -> {
                          addSaleSubscriptionUseCase.addSubscription(
                              searchAdiClubMemberResponse.toAdiClubMember());
                          return (Void) null;
                        })
                    .onErrorResume(
                        WebClientResponseException.class,
                        e ->
                            HttpStatus.NOT_FOUND.equals(e.getStatusCode())
                                ? Mono.fromRunnable(
                                        () ->
                                            addSaleSubscriptionUseCase.addSubscription(
                                                new UnregisteredUser(userEmail)))
                                    .mapNotNull(v -> null)
                                : Mono.error(
                                    new ResponseStatusException(
                                        e.getStatusCode(), e.getMessage()))));
  }
}
