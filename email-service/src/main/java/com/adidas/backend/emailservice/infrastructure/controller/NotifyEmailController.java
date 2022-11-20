package com.adidas.backend.emailservice.infrastructure.controller;

import com.adidas.backend.emailservice.application.NotifyEmailUseCase;
import com.adidas.backend.emailservice.domain.InvalidEmailException;
import com.adidas.backend.emailservice.infrastructure.controller.request.NotifyUserEmailRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(
    value = "/email",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public final class NotifyEmailController {
  private final NotifyEmailUseCase notifyEmailUseCase;

  public NotifyEmailController(final NotifyEmailUseCase notifyEmailUseCase) {
    this.notifyEmailUseCase = notifyEmailUseCase;
  }

  @PostMapping("/notifications")
  public Mono<Void> notifyUserEmail(
      @RequestBody final NotifyUserEmailRequest notifyUserEmailRequest) {

    return Mono.fromCallable(notifyUserEmailRequest::toUserEmail)
        .onErrorMap(InvalidEmailException.class, e -> new ServerWebInputException(e.getMessage()))
        .mapNotNull(
            userEmail -> {
              notifyEmailUseCase.notifyUserEmail(userEmail);
              return null;
            });
  }
}
