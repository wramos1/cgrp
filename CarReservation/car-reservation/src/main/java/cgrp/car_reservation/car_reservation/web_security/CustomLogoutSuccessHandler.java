package cgrp.car_reservation.car_reservation.web_security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: CustomLogoutSuccessHandler<br>
 * Date of Code: November 20, 2024<br>
 * Programmer's Name:<br>
 *
 * Brief Description:<br>
 * This class implements the LogoutSuccessHandler interface to handle
 * user logout events<br>
 * in a Spring Security application. Upon successful logout, it sends a
 * structured JSON response
 * to the client, providing feedback on the logout status.<br>
 *
 * Important Functions:<br>
 * 1. onLogoutSuccess:<br>
 * - Input:<br>
 * - HttpServletRequest request: The incoming HTTP request.<br>
 * - HttpServletResponse response: The HTTP response to be customized.<br>
 * - Authentication authentication: Contains details of the authenticated user
 * (if applicable).<br>
 * - Output:<br>
 * - None (void method).<br>
 * - Description:<br>
 * This method sets the HTTP response status code to 200 (OK) and content type
 * to JSON.<br>
 * It then constructs a JSON response body containing a logout success message
 * and status.<br>
 * The `ObjectMapper` from the Jackson library is used to convert the `Map` of
 * response data
 * to a JSON string, which is written to the response body.<br>
 *
 * Important Data Structures:<br>
 * - `MapString, String>`: Used to store key-value pairs representing the
 * response message and status.<br>
 * - `ObjectMapper`: Utilized to serialize the response data into a JSON string.<br>
 *
 * Algorithms Used:<br>
 * - The method relies on standard HTTP response handling and JSON serialization
 * practices.<br>
 * - The Jackson `ObjectMapper` was chosen for its simplicity and effectiveness
 * in converting Java
 * objects to JSON format.<br>
 */

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            org.springframework.security.core.Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Logout successful");
        responseBody.put("status", "success");

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
}
