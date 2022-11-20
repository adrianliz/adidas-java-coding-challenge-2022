package com.adidas.backend.prioritysaleservice.infrastructure.webclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient adiclubWebClient(@Value("${adiclub.endpoint}") final String adiclubEndpoint) {
    return WebClient.builder().baseUrl(adiclubEndpoint).build();
  }
}
