package cgrp.car_reservation.car_reservation;

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
