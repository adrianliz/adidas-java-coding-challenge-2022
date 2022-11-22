package com.adidas.backend.publicservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration implements WebFluxConfigurer {

  private final String jwkSetUri;

  public SecurityConfiguration(
      @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") final String jwtSetUri) {
    jwkSetUri = jwtSetUri;
  }

  @Bean
  public WebClient webClient() {
    return WebClient.builder().build();
  }

  @Bean
  public SecurityWebFilterChain securityFilterChain(
      final ServerHttpSecurity http, final WebClient webClient) {

    http.csrf()
        .disable()
        .authorizeExchange()
        .pathMatchers(HttpMethod.OPTIONS, "/**")
        .permitAll()
        .pathMatchers(
            "/webjars/**", "/swagger-ui.html", "/swagger-ui/index.html", "/v3/api-docs/**")
        .permitAll()
        .pathMatchers(HttpMethod.POST, "/priority-sale/**")
        .permitAll()
        .anyExchange()
        .authenticated()
        .and()
        .oauth2ResourceServer()
        .jwt()
        .jwtDecoder(NimbusReactiveJwtDecoder.withJwkSetUri(jwkSetUri).webClient(webClient).build());

    return http.build();
  }
}
