package com.ericramirezs.tasklistapp.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Component responsible for extracting and validating the API key from the incoming HTTP request.
 * If the provided API key matches the expected key, it returns an {@link Authentication} object.
 */
@Component
@RequiredArgsConstructor
public class ApiKeyAuthExtractor {

    private final RestApiKeyProperties apiKeyProperties;

    /**
     * Extracts the API key from the request header and validates it.
     *
     * @param request the {@link HttpServletRequest} containing the API key in the header.
     * @return an {@link Optional} containing the {@link Authentication} object if the API key is valid,
     *         otherwise an empty {@link Optional}.
     */
    public Optional<Authentication> extract(HttpServletRequest request) {
        String providedKey = request.getHeader("X-API-KEY");
        if (providedKey == null || !providedKey.equals(apiKeyProperties.getAuthToken())) {
            return Optional.empty();
        }
        return Optional.of(new ApiKeyAuth(providedKey, AuthorityUtils.NO_AUTHORITIES));
    }
}
