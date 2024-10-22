package cgrp.car_reservation.car_reservation;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.slf4j.Logger;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Define a CORS filter bean
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }

    //Define a CORS configuration source bean
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Allow your frontend
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow
        // necessary
        // HTTP
        // methods
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Allow required
        // headers
        configuration.setAllowCredentials(true); // Allow credentials (if needed)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all paths
        return source;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain...");
        http
                .csrf(csrf -> csrf.disable())  // For simplicity, disabling CSRF (Not recommended for production)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/",
                                "/login.html",
                                "/login",
                                "/register.html",
                                "/register",
                                "/homepage",
                                "/home",
                                "/login?error=true",
                                "/reservations/reservation",
                                "/register-user.html",
                                "/reservations"  // Ensure this matches your endpoint
                        ).permitAll()  // Allow these paths without authentication
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/reservations", true)  // Redirect after successful login
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll() // Allow everyone to access logout
                );
        logger.info("Security filter chain configured successfully.");
        return http.build();  // Build and return the SecurityFilterChain
    }

}