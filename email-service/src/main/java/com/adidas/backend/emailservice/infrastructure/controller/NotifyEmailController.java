package com.adidas.backend.emailservice.infrastructure.controller;

import com.adidas.backend.emailservice.application.NotifyUserUseCase;
import com.adidas.backend.emailservice.domain.InvalidEmailException;
import com.adidas.backend.emailservice.infrastructure.controller.request.NotifyUserRequestBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/email", consumes = MediaType.APPLICATION_JSON_VALUE)
public final class NotifyEmailController {
  private final NotifyUserUseCase notifyUserUseCase;

  public NotifyEmailController(final NotifyUserUseCase notifyUserUseCase) {
    this.notifyUserUseCase = notifyUserUseCase;
  }

  @PostMapping("/notifications")
  public Mono<Void> notifyUser(@RequestBody final NotifyUserRequestBody notifyUserRequestBody) {

    return Mono.fromCallable(notifyUserRequestBody::getUserEmail)
        .onErrorMap(InvalidEmailException.class, e -> new ServerWebInputException(e.getMessage()))
        .mapNotNull(
            userEmail -> {
              notifyUserUseCase.notifyUser(userEmail);
              return null;
            });
  }
}
