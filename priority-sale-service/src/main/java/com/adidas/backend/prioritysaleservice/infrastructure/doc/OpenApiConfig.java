package com.adidas.backend.prioritysaleservice.infrastructure.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class OpenApiConfig {
  @Bean
  @Primary
  public OpenAPI config(@Value("${open-api.gateway-public-uri}") final String gatewayPublicUri) {
    return new OpenAPI()
        .info(
            new Info()
                .title("Priority Sale API")
                .description("API to manage access to the sale.")
                .version("1.0.0"))
        .servers(List.of(new Server().url(gatewayPublicUri)));
  }
}
