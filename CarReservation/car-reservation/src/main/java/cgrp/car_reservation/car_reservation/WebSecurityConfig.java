package cgrp.car_reservation.car_reservation;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;


@EnableWebSecurity @Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    public WebSecurityConfig(CustomUserDetailsService UserDetailsService){
        this.userDetailsService = UserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // For simplicity, disabling CSRF (Not recommended for production)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/"// Ensure this matches your endpoint
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,"/**").permitAll()
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .formLogin(form -> form.disable()
                        //.defaultSuccessUrl("/reservations", true)  // Redirect after successful login
                        //.permitAll()
                )
                .logout(logout -> logout
                      .permitAll() // Allow everyone to access logout
                );
        return http.build();  // Build and return the SecurityFilterChain
    }
}

