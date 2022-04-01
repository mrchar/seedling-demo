package com.example.seedling.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeHttpRequests(
            (authorize) -> {
              authorize.antMatchers(HttpMethod.POST, "/login").permitAll();
              authorize.antMatchers(HttpMethod.GET, "/book/*").permitAll();
              authorize.anyRequest().authenticated();
            })
        // TODO: 启用防御跨站请求伪造策略
        .csrf()
        .disable()
        .httpBasic(withDefaults())
        .formLogin()
        .disable();
    return httpSecurity.build();
  }
}
