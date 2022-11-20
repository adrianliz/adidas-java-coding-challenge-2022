package com.adidas.backend.prioritysaleservice.infrastructure.controller;

import com.adidas.backend.prioritysaleservice.application.GenerateNextSaleAccessUseCase;
import com.adidas.backend.prioritysaleservice.application.response.SaleAccessResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public final class GenerateNextSaleAccessController extends PrioritySaleController {
  private final GenerateNextSaleAccessUseCase generateNextSaleAccessUseCase;

  public GenerateNextSaleAccessController(
      final GenerateNextSaleAccessUseCase generateNextSaleAccessUseCase) {
    this.generateNextSaleAccessUseCase = generateNextSaleAccessUseCase;
  }

  @PostMapping("/accesses")
  public Mono<SaleAccessResponse> generateNextAccess() {
    return generateNextSaleAccessUseCase.generateNextAccess().map(Mono::just).orElse(Mono.empty());
  }
}
