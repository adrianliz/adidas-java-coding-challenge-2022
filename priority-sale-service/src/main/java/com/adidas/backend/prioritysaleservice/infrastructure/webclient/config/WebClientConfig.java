package com.adidas.backend.prioritysaleservice.infrastructure.webclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(
      final ReactiveClientRegistrationRepository clientRegistrationRepository,
      final ReactiveOAuth2AuthorizedClientService clientService) {

    final ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider =
        ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
            .authorizationCode()
            .refreshToken()
            .clientCredentials()
            .password()
            .build();

    final AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager =
        new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
            clientRegistrationRepository, clientService);
    authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

    return authorizedClientManager;
  }

  @Bean
  public ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2Client(
      final ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
    final var oauth2Client =
        new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
    oauth2Client.setDefaultClientRegistrationId("priority-sale-service");
    oauth2Client.setDefaultOAuth2AuthorizedClient(true);
    return oauth2Client;
  }

  @Bean
  public WebClient adiclubWebClient(
      @Value("${adiclub.endpoint}") final String adiclubEndpoint,
      final ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2Client) {

    return WebClient.builder().baseUrl(adiclubEndpoint).filter(oauth2Client).build();
  }

  @Bean
  public WebClient emailWebClient(
      @Value("${email.endpoint}") final String emailEndpoint,
      final ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2Client) {

    return WebClient.builder().baseUrl(emailEndpoint).filter(oauth2Client).build();
  }
}
