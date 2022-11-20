package com.adidas.backend.prioritysaleservice.application;

import com.adidas.backend.prioritysaleservice.application.response.SaleAccessResponse;
import com.adidas.backend.prioritysaleservice.domain.SaleSubscriptions;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public final class GenerateNextSaleAccessUseCase {
  private final SaleSubscriptions saleSubscriptions;

  public GenerateNextSaleAccessUseCase(final SaleSubscriptions saleSubscriptions) {
    this.saleSubscriptions = saleSubscriptions;
  }

  public Optional<SaleAccessResponse> generateNextAccess() {
    return saleSubscriptions.generateNextAccess().map(SaleAccessResponse::from);
  }
}
