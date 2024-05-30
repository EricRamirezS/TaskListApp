package com.ericramirezs.tasklistapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * A filter that processes each HTTP request once to extract and validate an API key.
 * If a valid API key is found, it sets the authentication in the security context.
 */
@Component
@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final ApiKeyAuthExtractor extractor;

    /**
     * Filters the incoming request to extract the API key and set the authentication context.
     *
     * @param request     the {@link HttpServletRequest} being processed.
     * @param response    the {@link HttpServletResponse} being processed.
     * @param filterChain the {@link FilterChain} for invoking the next filter in the chain.
     * @throws ServletException if the filter chain cannot be executed.
     * @throws IOException      if an I/O error occurs during filtering.
     */
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        extractor.extract(request)
                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);

        filterChain.doFilter(request, response);
    }
}