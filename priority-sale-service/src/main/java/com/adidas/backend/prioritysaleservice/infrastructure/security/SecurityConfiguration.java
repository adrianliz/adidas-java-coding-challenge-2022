package com.adidas.backend.prioritysaleservice.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration implements WebFluxConfigurer {

  @Bean
  public SecurityWebFilterChain securityFilterChain(final ServerHttpSecurity http) {
    http.csrf().disable().authorizeExchange().anyExchange().permitAll();

    return http.build();
  }
}
