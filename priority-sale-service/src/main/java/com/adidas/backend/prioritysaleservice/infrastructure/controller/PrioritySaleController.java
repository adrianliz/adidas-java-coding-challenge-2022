package com.adidas.backend.prioritysaleservice.infrastructure.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(
    value = "/priority-sale",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public abstract class PrioritySaleController {}
