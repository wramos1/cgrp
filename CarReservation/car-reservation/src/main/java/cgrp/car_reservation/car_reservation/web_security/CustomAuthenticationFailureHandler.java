package cgrp.car_reservation.car_reservation.web_security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class Name: CustomAuthenticationFailureHandler
 * Date of Code: November 4, 2024
 * Programmer's Name:
 *
 * Description: This class provides a custom implementation of the
 * AuthenticationFailureHandler interface
 * to handle authentication failures in a Spring Security application. It
 * ensures that proper CORS
 * headers are included in the response, sets the status code to 401
 * (Unauthorized), and returns
 * a structured JSON error message to the client.
 *
 * Important Functions:
 * 1. onAuthenticationFailure:
 * - Input:
 * - HttpServletRequest request: The incoming HTTP request.
 * - HttpServletResponse response: The HTTP response to be customized.
 * - AuthenticationException exception: The exception thrown during the
 * authentication failure.
 * - Output:
 * - None (void method).
 * - Description:
 * This function sets custom HTTP response headers, including CORS headers, to
 * facilitate communication
 * between the backend and the frontend. It then sets the response status to 401
 * (Unauthorized), configures
 * the response content type to JSON, and writes an error message in JSON
 * format.
 *
 * Important Data Structures:
 * - HttpServletRequest and HttpServletResponse: Used to handle incoming
 * requests and outgoing responses.
 * - PrintWriter: Utilized to write a JSON error message to the response body.
 *
 * Algorithms Used:
 * - No specific algorithm is used in this implementation. Instead, the design
 * adheres to standard HTTP
 * response handling and CORS configuration practices. The decision to include
 * explicit CORS headers was
 * made to ensure proper frontend-backend communication in a development
 * environment where the frontend
 * and backend run on different origins.
 *
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); // Frontend origin
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // Set the response status code
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write("{\"error\": \"Authentication failed\"}");
        writer.flush();
    }
}
