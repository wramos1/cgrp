package cgrp.car_reservation.car_reservation.web_security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: CustomAuthenticationSuccessHandler
 * Date of Code: November 4, 2024
 * Programmer's Name:
 *
 * Brief Description:
 * This class provides a custom implementation of the
 * AuthenticationSuccessHandler interface
 * to handle successful authentication events in a Spring Security application.
 * It sends a JSON response
 * to the client containing user details such as username and roles, ensuring a
 * smooth flow of authentication
 * data between the backend and frontend. Proper CORS headers are also set to
 * support cross-origin requests.
 *
 * Important Functions:
 * 1. onAuthenticationSuccess:
 * - Input:
 * - HttpServletRequest request: The incoming HTTP request.
 * - HttpServletResponse response: The HTTP response to be customized.
 * - Authentication authentication: Contains details of the authenticated user.
 * - Output:
 * - None (void method).
 * - Description:
 * This method sets custom HTTP response headers to handle cross-origin
 * communication, sets the
 * status code to 200 (OK), and writes a JSON response containing user details
 * such as username
 * and roles. It extracts the authenticated user's details from the
 * `Authentication` object and
 * converts the data into a JSON structure using the Jackson library's
 * `ObjectMapper`.
 *
 * Important Data Structures:
 * - `UserDetails`: Represents the authenticated user details, including
 * username and roles.
 * - `Map<String, Object>`: Used to store and structure the user data in a
 * key-value format before
 * converting it to JSON.
 * - `ObjectMapper`: A utility class from the Jackson library for converting
 * Java objects to JSON.
 *
 * Algorithms Used:
 * - No specific algorithms are utilized; the implementation adheres to standard
 * HTTP and JSON handling practices.
 * - The `ObjectMapper` library was selected for its efficiency and simplicity
 * in converting Java objects to JSON
 * compared to manually constructing JSON strings or using other libraries.
 */

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); // Frontend origin
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // Set the response status code
        response.setStatus(HttpServletResponse.SC_OK); // or SC_CREATED for a different status
        response.setContentType("application/json");

        // Extract user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername(); // Get username
        // Optionally, you can include other user details such as roles or authorities
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("username", username);
        userResponse.put("roles", userDetails.getAuthorities());
        // Add any other necessary user details

        // Write the response as JSON
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(userResponse)); // Convert to JSON
        writer.flush();
    }
}
