package com.example.project_party.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Désactiver CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**", // Routes publiques (ajustez selon vos besoins)
                                "/v3/api-docs/**", // Swagger documentation
                                "/swagger-ui/**", // Swagger UI
                                "/swagger-ui.html" // Swagger UI HTML
                        ).permitAll() // Autoriser les requêtes publiques pour ces routes
                        .anyRequest().authenticated() // Nécessiter une authentification pour toutes les autres requêtes
                )
                .httpBasic(Customizer.withDefaults()); // Configurer HTTP Basic
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
