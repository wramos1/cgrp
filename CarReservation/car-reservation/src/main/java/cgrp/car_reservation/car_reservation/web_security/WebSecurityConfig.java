package cgrp.car_reservation.car_reservation.web_security;

import cgrp.car_reservation.car_reservation.user.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

        public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // Define a CORS configuration source bean
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Allow your frontend
                configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow
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
                http
                                .cors(withDefaults())
                                .csrf(AbstractHttpConfigurer::disable) // For simplicity, disabling CSRF (Not
                                                                       // recommended for
                                                                       // production)
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers(
                                                                "/",
                                                                "/login.html",
                                                                "/login",
                                                                "/register.html",
                                                                "/register",
                                                                "/homepage",
                                                                "/home/vehicles",
                                                                "/login?error=true",
                                                                "/reservations/reservation",
                                                                "/register-user.html",
                                                                "/login.html",
                                                                "/reservations" // Ensure this matches your endpoint
                                                ).permitAll()// Allow these paths without authentication
                                                .requestMatchers(
                                                                "/admin/**")
                                                .hasAuthority("ADMIN")
                                                .anyRequest().authenticated() // Require authentication for all other
                                                                              // requests
                                )
                                .formLogin(form -> form
                                                .loginPage("http://localhost:3000/#/login")
                                                .loginProcessingUrl("/login")
                                                .successHandler(new CustomAuthenticationSuccessHandler())
                                                .failureHandler(new CustomAuthenticationFailureHandler())
                                                .permitAll())
                                .logout(LogoutConfigurer::permitAll)
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Create a
                                                                                                          // session if
                                                                                                          // needed
                                                .maximumSessions(1) // Optional: Limit the number of simultaneous
                                                                    // sessions
                                                .expiredUrl("/login?expired=true") // Redirect to login if session
                                                                                   // expires
                                );
                return http.build(); // Build and return the SecurityFilterChain
        }

}
