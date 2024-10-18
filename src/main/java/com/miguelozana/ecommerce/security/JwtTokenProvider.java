package com.miguelozana.ecommerce.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
  private final String SECRET_KEY = "jwt-token";
}
