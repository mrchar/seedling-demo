package com.example.seedling.system;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
  private final AuthenticationConfiguration authenticationConfiguration;
  private final AuthenticationManager authenticationManager;

  public AuthenticationController(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    this.authenticationConfiguration = authenticationConfiguration;
    this.authenticationManager = this.authenticationConfiguration.getAuthenticationManager();
  }

  @PostMapping("/login")
  public void login(@RequestBody UsernamePasswordRequest request) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
