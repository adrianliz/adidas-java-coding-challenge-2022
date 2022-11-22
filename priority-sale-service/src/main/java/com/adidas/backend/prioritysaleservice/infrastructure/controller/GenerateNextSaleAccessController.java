package com.adidas.backend.prioritysaleservice.infrastructure.controller;

import com.adidas.backend.prioritysaleservice.application.GenerateNextSaleAccessUseCase;
import com.adidas.backend.prioritysaleservice.application.response.SaleAccessResponse;
import com.adidas.backend.prioritysaleservice.infrastructure.webclient.request.NotifyUserEmailRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

@RestController
@Slf4j
public final class GenerateNextSaleAccessController extends PrioritySaleController {
  private static final Integer SEND_MAIL_MAX_RETRIES = 10;

  private final GenerateNextSaleAccessUseCase generateNextSaleAccessUseCase;
  private final WebClient emailWebClient;
  private final String emailSendEndpoint;

  public GenerateNextSaleAccessController(
      final GenerateNextSaleAccessUseCase generateNextSaleAccessUseCase,
      final WebClient emailWebClient,
      @Value("${email.send.endpoint}") final String emailSendEndpoint) {
    this.generateNextSaleAccessUseCase = generateNextSaleAccessUseCase;
    this.emailWebClient = emailWebClient;
    this.emailSendEndpoint = emailSendEndpoint;
  }

  @PostMapping(value = "/accesses", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Generates next access to the sale.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = SaleAccessResponse.class)))
      })
  public Mono<SaleAccessResponse> generateNextAccess() {
    return generateNextSaleAccessUseCase
        .generateNextAccess()
        .map(Mono::just)
        .orElse(Mono.empty())
        .doOnNext(
            response ->
                emailWebClient
                    .post()
                    .uri(emailSendEndpoint)
                    .bodyValue(NotifyUserEmailRequest.from(response))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .retryWhen(
                        Retry.backoff(SEND_MAIL_MAX_RETRIES, Duration.ofSeconds(10))
                            .doAfterRetry(
                                retrySignal ->
                                    log.error(
                                        "Sending email to {} failed. Retry {}/{}",
                                        response.userEmail(),
                                        retrySignal.totalRetries() + 1,
                                        SEND_MAIL_MAX_RETRIES))
                            .onRetryExhaustedThrow(
                                (retryBackoffSpec, retrySignal) -> {
                                  log.error(
                                      "Sending email to {} failed failed after {} retries",
                                      response.userEmail(),
                                      SEND_MAIL_MAX_RETRIES);
                                  return retrySignal.failure();
                                }))
                    .subscribeOn(Schedulers.boundedElastic())
                    .subscribe());
  }
}
