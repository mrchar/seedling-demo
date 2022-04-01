package com.example.seedling.interfaces;

import com.example.seedling.interfaces.request.UsernamePasswordRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "身份验证")
@RestController
public class AuthenticationController {
  private final AuthenticationConfiguration authenticationConfiguration;
  private final AuthenticationManager authenticationManager;

  public AuthenticationController(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    this.authenticationConfiguration = authenticationConfiguration;
    this.authenticationManager = this.authenticationConfiguration.getAuthenticationManager();
  }

  @Operation(summary = "登录")
  @PostMapping("/login")
  public void login(@RequestBody UsernamePasswordRequest request) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
