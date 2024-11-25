package cgrp.car_reservation.car_reservation.web_security;

import cgrp.car_reservation.car_reservation.user.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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

/**
 * Class Name: WebSecurityConfig
 * Date of Code: November 18, 2024
 * Programmer's Name:
 *
 * Brief Description:
 * This class configures Spring Security for the application. It sets up
 * authentication and authorization
 * rules, integrates custom handlers for login and logout, configures CORS
 * policies, and manages session
 * behavior. The configuration ensures secure access control while allowing
 * cross-origin communication with
 * the frontend hosted at http://localhost:3000.
 *
 * Important Functions:
 * 1. passwordEncoder:
 * - Input: None.
 * - Output: PasswordEncoder object.
 * - Description:
 * Creates and returns a `BCryptPasswordEncoder` bean for securely hashing and
 * verifying passwords.
 *
 * 2. corsConfigurationSource:
 * - Input: None.
 * - Output: CorsConfigurationSource object.
 * - Description:
 * Configures Cross-Origin Resource Sharing (CORS) to allow requests from the
 * specified frontend
 * origin (http://localhost:3000), along with allowed methods, headers, and
 * credentials.
 *
 * 3. securityFilterChain:
 * - Input: HttpSecurity object.
 * - Output: SecurityFilterChain object.
 * - Description:
 * Configures the security filter chain with the following features:
 * - Disables CSRF for simplicity (not recommended for production).
 * - Sets up authorization rules to allow unauthenticated access to specific
 * endpoints and restrict
 * access to `/admin/**` to users with the "ADMIN" authority.
 * - Configures custom login and logout handlers.
 * - Manages session creation and limits simultaneous sessions to one per user.
 *
 * Key Customizations:
 * - **CORS Configuration**: Ensures smooth interaction between the backend and
 * frontend by allowing
 * specific origins, methods, and headers.
 * - **Custom Handlers**:
 * - `CustomAuthenticationSuccessHandler`: Handles successful login events.
 * - `CustomAuthenticationFailureHandler`: Handles failed login attempts.
 * - `CustomLogoutSuccessHandler`: Provides a structured response on successful
 * logout.
 * - **Session Management**: Restricts simultaneous sessions and handles session
 * expiration gracefully.
 *
 * Important Data Structures:
 * - `CorsConfiguration` and `UrlBasedCorsConfigurationSource`: Used to define
 * and register CORS rules.
 * - `HttpSecurity`: Configures the security filter chain, managing
 * authentication, authorization, and session handling.
 */

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)  // Enables @PreAuthorize annotation
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
                                                                "/home/keyword/*",
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
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessHandler(new CustomLogoutSuccessHandler()) // Use custom
                                                                                                        // logout
                                                                                                        // success
                                                                                                        // handler
                                                .invalidateHttpSession(true) // Invalidate the session
                                                .deleteCookies("JSESSIONID") // Delete session cookies
                                                .permitAll())
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
