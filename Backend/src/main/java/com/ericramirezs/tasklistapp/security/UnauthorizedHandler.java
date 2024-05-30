package com.ericramirezs.tasklistapp.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Handles unauthorized access attempts by sending a 401 Unauthorized response.
 * Implements {@link AuthenticationEntryPoint} to commence the authentication process.
 */
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {

    /**
     * Sends a 401 Unauthorized error response when an authentication exception occurs.
     *
     * @param request       the {@link HttpServletRequest} being processed.
     * @param response      the {@link HttpServletResponse} to which the error response is sent.
     * @param authException the exception that triggered this unauthorized response.
     * @throws IOException if an I/O error occurs while sending the error response.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

}