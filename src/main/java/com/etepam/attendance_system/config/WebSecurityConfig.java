package com.etepam.attendance_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Permite todas as requisições sem autenticação
        http
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest.anyRequest().permitAll() // Todas as rotas são públicas
                )
                .csrf(csrf -> csrf.disable()); // Desativa CSRF, se necessário

        return http.build();
    }
}


