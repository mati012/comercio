package com.example.microinventariokafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf().disable()                       // deshabilita CSRF
      .authorizeHttpRequests()
        .requestMatchers("/api/**").permitAll() // permite todo en /api/**
      .anyRequest().authenticated()
      .and().httpBasic();                    // mantiene httpBasic pero no es necesario
    return http.build();
  }
}
