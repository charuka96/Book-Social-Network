package com.example.book_network.security;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

private final AuthenticationProvider authenticationProvider;
private final JwtFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req->
                        req.requestMatchers(
                                "/auth/**",
                                         "/v2/api-docs",
                                        "/v3/api-docs",
                                "/v3/api-docs/**",
                                "swagger-resources",
                                        "swagger-resources/*",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "webjars/**",
                                "/swagger-ui.html"
                        ).permitAll().anyRequest()
                                .authenticated()
                )
                .sessionManagement(session->session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore((Filter) jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return null;
    }
}
