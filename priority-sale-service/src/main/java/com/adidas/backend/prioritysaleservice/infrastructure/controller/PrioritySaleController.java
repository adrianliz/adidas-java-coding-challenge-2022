package com.adidas.backend.prioritysaleservice.infrastructure.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Priority Sale", description = "Operations related to obtain access to the sale.")
@RequestMapping(value = "/priority-sale")
public abstract class PrioritySaleController {}
