package com.example.seedling.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeHttpRequests(
            (authorize) -> {
              authorize.antMatchers("/swagger-ui/**").permitAll();
              authorize.antMatchers("/v3/api-docs").permitAll();
              authorize.antMatchers(HttpMethod.POST, "/login").permitAll();
              //              authorize.antMatchers(HttpMethod.GET, "/book/*").hasRole(Roles.USER);
              //              authorize.antMatchers(HttpMethod.POST, "/book").hasRole(Roles.ADMIN);
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
